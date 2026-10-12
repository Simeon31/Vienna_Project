ALTER TABLE inventory_item
    ADD COLUMN product_id BIGINT;

UPDATE inventory_item
SET product_id = id
WHERE product_id IS NULL;

ALTER TABLE inventory_item
    ALTER COLUMN product_id SET NOT NULL;

ALTER TABLE inventory_item
    ADD COLUMN demand_in_units REAL NOT NULL DEFAULT 0
        CHECK (demand_in_units >= 0);

ALTER TABLE inventory_item
    ADD CONSTRAINT inventory_item_product_warehouse_key
        UNIQUE (product_id, warehouse_id);
