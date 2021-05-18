module.exports = {
    devServer: {
        port: 8081,
        proxy: {
            "/todo": {
                target: "http://localhost:8080",
                secure: false
            }
        }
    }
};