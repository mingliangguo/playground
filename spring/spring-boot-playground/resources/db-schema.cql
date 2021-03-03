# prepare data
CREATE KEYSPACE IF NOT EXISTS test_keyspace
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }; }

use test_keyspace;

create table entities (id text, name text, primary key (id));
insert into entities(id, name) values ('1', 'aa');
insert into entities(id, name) values ('2', 'bb');
insert into entities(id, name) values ('3', 'cc');
insert into entities(id, name) values ('4', 'dd');
insert into entities(id, name) values ('5', 'ee');


create table composite_entities (first_id text, second_id text, third_id text, name text, primary key (first_id, second_id, third_id));
insert into composite_entities(first_id, second_id, third_id, name) values ('1', '2', '3', 'dd');
insert into composite_entities(first_id, second_id, third_id, name) values ('1', '3', '4', 'ee');
insert into composite_entities(first_id, second_id, third_id, name) values ('1', '4', '5', 'ff');
