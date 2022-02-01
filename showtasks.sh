if ./runcrud.sh; then
    echo Success
else
    echo Failure
fi

x-www-browser http://localhost:9090/crud/v1/task/getTasks