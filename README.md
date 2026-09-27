# Warehouse Optimization

Warehouse Optimization is a full-stack application with a React client and a Spring Boot backend organized with vertical slicing and clean architecture principles.

## Workflow overview

The application follows this runtime workflow:

1. The user interacts with the web UI (`client`).
2. The UI calls backend HTTP endpoints exposed by inbound adapters.
3. Inbound adapters delegate to application use cases.
4. Use-case services coordinate business flow via domain objects and output ports.
5. Output adapters provide infrastructure access (PostgreSQL in production, in-memory adapter in `dev`).
6. The response is mapped back to DTOs and returned to the client.

This keeps transport, business logic, and persistence separated while preserving feature-oriented module ownership.

## Backend architecture workflow (server)

The backend lives under `server/src/main/java/com/warehouseoptimizer/warehouse_optimization_app` and is structured by module (vertical slice), e.g. `modules/inventory`.

Each module follows a clean architecture path:

- `infrastructure/web` → inbound HTTP adapter
- `application/ports/in` → use-case contract (`Verb + Noun + UseCase`)
- `application/services` → use-case implementation (`Verb + Noun + Service`)
- `application/ports/out` → outbound contract (`Noun + Repository|Gateway`)
- `infrastructure/persistence` → outbound adapter (`PortName + Adapter`)
- `domain` → domain model and invariants
- `api` → shared contracts for other backend modules (Modulith named interface)

Inventory read workflow example:

`GET /inventory/warehouses/{warehouseId}/items/{sku}`

`FindInventoryStockUseCaseAdapter` → `FindInventoryStockUseCase` → `FindInventoryStockService` → `InventoryRepository` → `InventoryRepositoryAdapter` (prod) or `InventoryRepositoryInMemoryAdapter` (dev)

## Client-server communication workflow

- In local frontend development, Vite proxies `/inventory` requests to `http://localhost:8080`.
- In containerized deployment, the client and server run as separate services and the browser targets the published backend endpoint.
- The backend exposes CORS configuration through `APP_CORS_ALLOWED_ORIGINS`.

Recommended approach:

- Keep the Spring backend as the system API and business orchestration layer.
- Use frontend proxying only as a development convenience (not as domain orchestration).
- Keep controllers as inbound adapters only: request mapping, validation/mapping, delegation to use cases.

## PostgreSQL workflow (production-ready)

The backend is configured for PostgreSQL with Flyway migrations:

- Datasource is configured via environment variables.
- Flyway runs startup migrations from `classpath:db/migration`.
- JPA validates schema (`ddl-auto: validate`) and does not auto-create tables.

Default environment variables:

- `SPRING_DATASOURCE_URL` (default `jdbc:postgresql://localhost:5432/warehouse_optimization`)
- `SPRING_DATASOURCE_USERNAME` (default `warehouse`)
- `SPRING_DATASOURCE_PASSWORD` (default `warehouse`)
- `SPRING_PROFILES_ACTIVE` (`dev` by default, `prod` in compose)
- `APP_CORS_ALLOWED_ORIGINS` (default `http://localhost:3000`)

## Running the system

### Option 1: Docker Compose 

From project root:

```bash
docker compose up --build
```

Services:

- Client: `http://localhost:3000`
- Server: `http://localhost:8080`
- PostgreSQL: `localhost:5432`

### Option 2: Local development workflow

1. Start PostgreSQL and create credentials matching your environment variables.
2. Run backend from `server`.
3. Run frontend from `client` (`npm run dev`).

In this workflow, the frontend proxy forwards `/inventory/**` to the backend.

## Health and sanity check workflow

1. Verify backend health endpoint.
2. Query inventory endpoint:

```http
GET /inventory/warehouses/WH-AMS/items/SKU-001
```

Expected shape:

```json
{
  "sku": "SKU-001",
  "warehouseId": "WH-AMS",
  "onHand": 100,
  "reserved": 25,
  "availableQuantity": 75
}
```

## Notes

- Modulith boundaries are declared at module/API package level and should be kept stable as new modules are added.
