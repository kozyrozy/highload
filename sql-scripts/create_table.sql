create table dump_table(
id int not null,
dump_time datetime not null default '1970-01-01 00:00:01',
value_a float8 not null,
value_b float8 not null)
partition by range(year(dump_time)) (
	partition dump_table_arch values less than(2022),
	partition dump_table_2022 values less than(2023),
	partition dump_table_2023 values less than(maxvalue)
);

drop table dump_table;

delimiter |
create procedure generate_seq(n bigint unsigned)
begin
  declare i bigint unsigned default 0;
  while i < n do
    insert into seq_temp values(i);
    set i = i + 1;
  end while;
end|
delimiter ;

create temporary table seq_temp (value bigint unsigned not null primary key);

call generate_seq(1000);

insert into dump_table(id, dump_time, value_a, value_b)
	select
		floor(1+rand()*50000),
        timestampadd(hour,17520*(rand()*2),'2022-01-01 00:00:01'),
        rand()*200+1,
        rand()*300+1
	from seq_temp;
		
select * from dump_table
where dump_time >= date('2022-06-02');
        