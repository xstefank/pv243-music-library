var gulp = require('gulp');
var concat = require('gulp-concat');
var browserSync = require('browser-sync').create();
var reload = browserSync.reload;
var proxy = require('proxy-middleware');
var url = require('url');


var devMode = false;

gulp.task('css', function() {
    gulp.src('./src/main/webapp/css/*.css')
        .pipe(gulp.dest('./target/music-library-web-1.0-SNAPSHOT/css'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('js', function() {
    gulp.src('./src/main/webapp/js/*.js')
        .pipe(gulp.dest('./target/music-library-web-1.0-SNAPSHOT/js'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('partials-html', function() {
    return gulp.src('./src/main/webapp/partials/*.html')
        .pipe(gulp.dest('./target/music-library-web-1.0-SNAPSHOT/partials'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('partials-js', function() {
    return gulp.src('./src/main/webapp/partials/*.js')
        .pipe(gulp.dest('./target/music-library-web-1.0-SNAPSHOT/partials'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('html', function() {
    return gulp.src('./src/main/webapp/index.html')
        .pipe(gulp.dest('./target/music-library-web-1.0-SNAPSHOT'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('browser-sync', function () {
    var proxyOptions = url.parse('http://localhost:8080/music');
    proxyOptions.route = '/music';
    browserSync.init({
        open: true,
        // browser: "google chrome",
        browser: "firefox",
        server: {
            baseDir: './target/music-library-web-1.0-SNAPSHOT',
            middleware: [proxy(proxyOptions)]
        }
    });
});

gulp.task('js-watch', ['js'], function (done) {
    browserSync.reload();
    done();
});

gulp.task('start', function () {
    gulp.watch(['./src/main/webapp/css/*.css'], ['css']).on("change", reload);
    gulp.watch(['./src/main/webapp/js/*.js'], ['js']).on("change", reload);
    gulp.watch(['./src/main/webapp/partials/*.html'], ['partials-html']).on("change", reload);
    gulp.watch(['./src/main/webapp/partials/*.js'], ['partials-js']).on("change", reload);
    gulp.watch(['./src/main/webapp/index.html'], ['html']).on("change", reload);
});

gulp.task('default', ['browser-sync', 'css', 'js', 'html', 'start']);