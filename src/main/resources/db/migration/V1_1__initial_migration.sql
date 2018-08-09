create table fuel_consumptions
(
  id bigserial identity not null
    constraint fuel_consumption_pkey
    primary key,
  created_at timestamp,
  changed_at timestamp,
  fuel_type varchar(255),
  price_per_litter decimal(3,2),
  volume decimal(20,2),
  date timestamp,
  driver_id integer
);