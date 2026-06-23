CREATE TABLE food_item (
   id serial PRIMARY KEY ,
   name VARCHAR(255) NOT NULL,
   type VARCHAR(50) NOT NULL,
   quantity INT NOT NULL,
   valid_to DATE NOT NULL,
   CONSTRAINT chk_food_item_type CHECK (type IN ('MEAT', 'FRUIT', 'VEGETABLE', 'DAIRY'))
);