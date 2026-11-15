
export interface Shipment {
    id: number | null;
    warehouseId: number;
    orderStatus: string;
    expectedArrival: string | null;
    createdAt: string | null;
}

export interface ShipmentView {
    id: number | null;
    shipmentName: string;
    warehouseName: string;
    orderStatus: string;
    expectedArrival: string | null;
}
