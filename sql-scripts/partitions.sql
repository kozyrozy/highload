alter table dump_table
reorganize partition dump_table_arch into(
partition dump_table_arch values less than(2021),
partition dump_table_2021 values less than(2022)
);

alter table dump_table remove partitioning;

alter table dump_table
partition by range(year(dump_time)) (
	partition dump_table_arch values less than(2023),
	partition dump_table_actual values less than(2024),
	partition dump_table_future values less than(maxvalue)
);
