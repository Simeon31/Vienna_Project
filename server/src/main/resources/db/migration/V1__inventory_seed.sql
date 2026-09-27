CREATE TABLE IF NOT EXISTS inventory_item (
    sku VARCHAR(64) NOT NULL,
    warehouse_id VARCHAR(64) NOT NULL,
    on_hand INTEGER NOT NULL CHECK (on_hand >= 0),
    reserved INTEGER NOT NULL CHECK (reserved >= 0 AND reserved <= on_hand),
    PRIMARY KEY (sku, warehouse_id)
);

INSERT INTO inventory_item (sku, warehouse_id, on_hand, reserved)
VALUES ('SKU-001', 'WH-AMS', 100, 25),
       ('SKU-002', 'WH-AMS', 10, 10)
ON CONFLICT (sku, warehouse_id) DO NOTHING;