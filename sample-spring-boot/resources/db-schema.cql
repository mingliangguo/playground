CREATE KEYSPACE IF NOT EXISTS test_keyspace
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }; }

create table entities (id text, name text, primary key (id));
# prepare data
insert into entities values ('1', 'aa');
insert into entities values ('2', 'bb');
insert into entities values ('3', 'cc');
insert into entities values ('4', 'dd');
insert into entities values ('5', 'ee');
