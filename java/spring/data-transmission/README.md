# README

A sample spring application used to prototype transmit data from database to kafka and sink to a different database.

## Setup Kafka cluster

Use [Kafka Stack docker compose](https://github.com/simplesteph/kafka-stack-docker-compose) to spin up a kafka cluster using docker compose. The [single kafka with single zk](https://github.com/simplesteph/kafka-stack-docker-compose/blob/master/zk-single-kafka-single.yml) works great for development.

If you happen to run the docker container in a different machine, make sure you export the `DOCKER_HOST_IP` to the host name/ip which can be reachable from the client machine.

```bash
export DOCKER_HOST_IP=myRemoteHost(or ip)
docker-compose -f zk-single-kafka-single.yml up
docker-compose -f zk-single-kafka-single.yml down
```

To test with kafka cli:

```bash
wget https://apache.claz.org/kafka/2.7.0/kafka_2.13-2.7.0.tgz
tar -zxf kafka_2.13-2.7.0.tgz
cd kafka_2.13-2.7.0/bin
./kafka-topics.sh --bootstrap-server skywalker.local:9092 --list
```

## Setup SQL Server database

```bash
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=P@ssw0rd' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-latest
```

- Refer to: [Deploy and connect to SQL Server Docker containers](https://docs.microsoft.com/de-de/sql/linux/sql-server-linux-docker-container-deployment?view=sql-server-ver15&pivots=cs1-bash)

### Connect to SQL Server instance

[DBeaver Community](https://dbeaver.io/) is a very nice tool for database management.

```bash
brew install --cask dbeaver-community
```

