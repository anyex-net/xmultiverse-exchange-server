CREATE TABLE `assets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` longtext,
  `prec_save` bigint DEFAULT NULL,
  `prec_show` bigint DEFAULT NULL,
  `status` bigint DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_assets_deleted_at` (`deleted_at`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


CREATE TABLE `markets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` longtext,
  `stock` longtext,
  `money` longtext,
  `fee_prec` bigint DEFAULT NULL,
  `stock_prec` bigint DEFAULT NULL,
  `money_prec` bigint DEFAULT NULL,
  `min_amount` longtext,
  `status` bigint DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_markets_deleted_at` (`deleted_at`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*
matchengine config.json

{
        "alert": {
                "addr": "127.0.0.1:4444",
                "host": "matchengine"
        },
        "assets": [{
                "ID": 1,
                "CreatedAt": "2024-12-12T20:44:19+08:00",
                "UpdatedAt": "2024-12-12T20:44:22+08:00",
                "DeletedAt": null,
                "name": "USDT",
                "prec_save": 16,
                "prec_show": 8,
                "status": 1
        }, {
                "ID": 2,
                "CreatedAt": "2024-12-12T20:44:41+08:00",
                "UpdatedAt": "2024-12-12T20:44:44+08:00",
                "DeletedAt": null,
                "name": "BTC",
                "prec_save": 16,
                "prec_show": 8,
                "status": 1
        }, {
                "ID": 3,
                "CreatedAt": "2024-12-12T20:44:53+08:00",
                "UpdatedAt": "2024-12-12T20:44:55+08:00",
                "DeletedAt": null,
                "name": "ETH",
                "prec_save": 16,
                "prec_show": 8,
                "status": 1
        }],
        "brokers": "127.0.0.1:9092",
        "cli": "tcp@127.0.0.1:7317",
        "db_history": {
                "host": "112.14.45.72",
                "name": "trade_history",
                "pass": "2G4@Sky2023&*%FGJhliv2#23",
                "user": "skyadmin"
        },
        "db_log": {
                "host": "112.14.45.72",
                "name": "trade_log",
                "pass": "2G4@Sky2023&*%FGJhliv2#23",
                "user": "skyadmin"
        },
        "debug": true,
        "history_interval": 0.01,
        "log": {
                "flag": "fatal,error,warn,info,debug,trace",
                "num": 10,
                "path": "/var/log/trade2/matchengine"
        },
        "markets": [{
                "min_amount": "0.0000001",
                "money": {
                        "name": "USDT",
                        "prec": 8
                },
                "name": "ETH_USDT",
                "stock": {
                        "name": "ETH",
                        "prec": 8
                }
        }, {
                "min_amount": "0.0000001",
                "money": {
                        "name": "USDT",
                        "prec": 8
                },
                "name": "BTC_USDT",
                "stock": {
                        "name": "BTC",
                        "prec": 8
                }
        }],
        "operlog_interval": 0.01,
        "process": {
                "core_limit": 1000000000,
                "file_limit": 1000000
        },
        "slice_interval": 3600,
        "slice_keeptime": 259200,
        "svr": {
                "bind": ["tcp@0.0.0.0:7316", "udp@0.0.0.0:7316"],
                "buf_limit": 100,
                "heartbeat_check": false,
                "max_pkg_size": 10240
        }
}
*/

