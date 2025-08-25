CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(225),
    telegram_id BIGINT UNIQUE NOT NULL,
    auto_payment BOOLEAN NOT NULL DEFAULT false,
    balance DOUBLE PRECISION DEFAULT 0,
    register_date DATE
);

CREATE TABLE car_numbers (
    id UUID NOT NULL PRIMARY KEY,
    number VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE car_registers (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    number_id UUID NOT NULL,
    register_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (number_id) REFERENCES car_numbers(id)
);

CREATE TABLE transits (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    car_number_id UUID NOT NULL,
    accrual_number VARCHAR(255) NOT NULL,
    accrual_date DATE NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'UNPAID',
    FOREIGN KEY (car_number_id) REFERENCES car_numbers(id)
);

CREATE TABLE transactions (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_date DATE,
    amount DOUBLE PRECISION,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE billing_payments (
    id UUID NOT NULL PRIMARY KEY,
    transaction_id UUID NOT NULL,
    transit_id UUID,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id),
    FOREIGN KEY (transit_id) REFERENCES transits(id)
);

CREATE INDEX idx_car_registers_user_id ON car_registers(user_id);
CREATE INDEX idx_car_registers_number_id ON car_registers(number_id);
CREATE INDEX idx_transits_car_number_id ON transits(car_number_id);
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_billing_payments_transaction_id ON billing_payments(transaction_id);
CREATE INDEX idx_billing_payments_transit_id ON billing_payments(transit_id);