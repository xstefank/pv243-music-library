var gulp = require('gulp');
var concat = require('gulp-concat');
var browserSync = require('browser-sync').create();
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

gulp.task('partials', function() {
    return gulp.src('./src/main/webapp/partials/*.html')
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

gulp.task('browser-sync', function() {
    var proxyOptions = url.parse('http://localhost:8080/music');
    proxyOptions.route = '/music';
    browserSync.init(null, {
        open: true,
        server: {
            baseDir: './target/music-library-web-1.0-SNAPSHOT',
            middleware: [proxy(proxyOptions)]
        }
    });
});

gulp.task('start', function() {
    devMode = true;
    gulp.watch(['./src/main/webapp/css/*.css'], ['css']);
    gulp.watch(['./src/main/webapp/js/*.js'], ['js']);
    gulp.watch(['./src/main/webapp/partials/*.html'], ['html']);
    gulp.watch(['./src/main/webapp/index.html'], ['html']);
});

gulp.task('default', ['browser-sync', 'css', 'js', 'html', 'partials', 'start']);