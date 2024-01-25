drop table if exists transfer_rate;

create table if not exists transfer_rate
(
    id            bigint         not null,
    from_day      integer        not null,
    until_day     integer        not null,
    transfer_rate numeric(38, 2) not null,
    status        boolean,
    primary key (id)
);

drop table if exists scheduling;

create table if not exists scheduling
(
    uuid                uuid           not null,
    origin_account      bigint         not null,
    destination_account bigint         not null,
    transfer_amount     numeric(38, 2) not null,
    scheduling_date     date           not null,
    transfer_date       date           not null,
    transfer_status     tinyint        not null check (transfer_status between 0 and 3),
    transfer_rate_id    bigint
        constraint transfer_rate_fk
            references transfer_rate,
    primary key (uuid)

);