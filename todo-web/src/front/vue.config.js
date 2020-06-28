const port = 8090;
const proxyPort = 8080;

module.exports = {

    publicPath: '',

    outputDir: '../main/resources/static',

    chainWebpack(config) {

        config.output.filename("js/App.js");

    },

    lintOnSave: false,
    runtimeCompiler: true,
    devServer: {
        port: port,
        open: true,
        overlay: {
            warnings: false,
            errors: true
        },
        proxy: {
            '/api': {
                target: `http://localhost:${proxyPort}`,
                ws: false
            }
        }
    },

};