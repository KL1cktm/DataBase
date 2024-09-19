CREATE TABLE IF NOT EXISTS suppliers
(
    id     INTEGER PRIMARY KEY NOT NULL,
    name   TEXT                NOT NULL,
    status INTEGER             NOT NULL,
    city   TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    id    INTEGER PRIMARY KEY NOT NULL,
    name  TEXT                NOT NULL,
    color TEXT                NOT NULL,
    size  INTEGER             NOT NULL,
    city  TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS projects
(
    id   INTEGER PRIMARY KEY NOT NULL,
    name TEXT                NOT NULL,
    city TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS counts
(
    supplier_id INTEGER NOT NULL,
    product_id  INTEGER NOT NULL,
    project_id  INTEGER NOT NULL,
    count       INTEGER NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);