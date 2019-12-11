#!/usr/bin/env bash

wrk -t1 -c1 -d10s http://127.0.0.1:8080
wrk -t1 -c4 -d10s http://127.0.0.1:8080
wrk -t4 -c4 -d10s http://127.0.0.1:8080
wrk -t4 -c10 -d10s http://127.0.0.1:8080
wrk -t4 -c100 -d10s http://127.0.0.1:8080
wrk -t10 -c10 -d10s http://127.0.0.1:8080
wrk -t10 -c100 -d10s http://127.0.0.1:8080
wrk -t10 -c200 -d10s http://127.0.0.1:8080
wrk -t10 -c500 -d10s http://127.0.0.1:8080
