var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;

// hack for renaming file for cssmin having '.' in filename
var rename = {
    ext: function(ext) {
        return function (dest, src) {
            return dest + "/" + src.replace(/(\.[^\/\.]*)?$/, ext);
        }
    },
};

// Gruntfile.js
module.exports = function(grunt) {

    // Configure
    grunt.initConfig({

        working_base_folder: '/gohack/src/main/resources/static',

        working_base_assets:'/gohack/src/main/resources/static',
        // Dev
        dev_base_folder: 'dev',
        dev_server_port: 8080,
        // ---
        pkg: grunt.file.readJSON('package.json'),
        banner: '/*! ' + [
            '<%= pkg.description %> v<%= pkg.version %>',
            'Copyright (c) <%= grunt.template.today("yyyy") %>',
            '<%= grunt.template.today("ddd, dd mmm yyyy HH:MM:ss Z") %>'
            ].join(' | ') + ' */',

        // Watch task.
        // we can always off a task from being watched , if we don't need it
        // intention is to automate the development & take out the manual interventions
        watch: {
            options: {
                livereload: true,
            },
            gruntfile: {
                files: 'gruntFile.js',
                // The tasks to run
                tasks: [
                    'watchdefault',
                ],
            },

            //  if we are not doin any thing with JS , so don't watch changes :)
            scripts: {
                files: [
                    'js/jquery.js',
                    'js/jquery.fittext.js',
                    'js/jquery.easing.min.js',
                    'js/jquery.easyPaginate.js',
                    'js/moment.min.js',
                    'js/wow.min.js',
                    'js/login.js',
                    'js/ideaPage.js',
                    'js/idea.js',
                    'js/export.js',
                    'js/creative.js',
                    'js/countdown.js',
                    'js/cbpAnimatedHeader.js',
                    'js/bootstrap.min.js',
                    '!js/gohack.js',
                    '!js/gohack.min.js',
                ],
                tasks: [
                    'clean:jsfiles',
                ],
                options: {
                    livereload: true,
                },
            },

            finalscripts: {
                files: [
                    'js/gohack.js',
                    'js/gohack.min.js',
                ],
                options: {
                    event: ['deleted'],
                    livereload: true,
                },
                // The tasks to run
                tasks: [
                    'clean:jsfiles',
                    'concat:hackjs',
                    'uglify:hackjs',
                ],
            },

            // Keep an eye on those stylesheets.
            styles: {
                files: [
                    'css/*.css',
                    '!css/gohack.css',  
                    '!css/gohack.min.css',  
                ],
                // The tasks to run
                tasks: [
                    'clean:cssfiles',
                ],
                options: {
                   
                },
            },

            finalstyles: {
                files: [
                    'css/gohack.css',
                    'css/gohack.min.css',
                ],

                // The tasks to run
                tasks: [
                    'clean:cssfiles',
                    'concat:hackcss',
                    'cssmin:minify',
                ],
                options: {
                    event: ['deleted'],
                },
            },
        },
        // put a banner on final CSS , JS & VENDOR SCRIPTS -
        // to give glimpse to dev when was last modified & along with code package version notes
        usebanner: {
            dev: {
                options: {
                    position: 'top',
                    banner: '<%= banner %>',
                },
                files: {
                    src: [
                        'css/gohack.css',
                        'js/gohack.js',
                    ],
                },
            },
        },

        // maintain copy of your local files before doin any action
        // this will save you if something terribly goes wrong & but don't commit
        copy: {
            dev: {
                files: [
                    {
                        expand: true,
                        cwd: '',
                        src: [
                            'js/**/*',
                            'css/**/*',
                        ],
                        dest: '<%= dev_base_folder %>/',
                    },
                ],
            },

        },

        //  yes , this task will make your lovable code looks ugly ... but for good reason
        uglify: {
            hackjs: {
                files: {
                    'js/gohack.min.js': ['js/gohack.js'],
                },
                options: {
                  mangle: false
                }
            }
        },

        cssmin: {
            minify: {
                files: [{
                    expand: true,
                    cwd: 'css/',
                    src: ['gohack.css'],
                    dest: 'css/',
                    ext: '.min.css'
                }]
            },
        },

        //  clean the newly created css , if not required comment
        //  purpose is to clean from cached
        clean: {
            dev: [
                'css/gohack.css',
                'css/gohack.min.css',
                'js/gohack.js',
                'js/gohack.min.js',
            ],

            cssfiles: [
                'css/gohack.css',
                'css/gohack.min.css',
            ],

            jsfiles: [
                'js/gohack.js',
                'js/gohack.min.js',
            ],
        },
        concat: {
            hackcss: {
                src: [
                    'css/*.css',
                    '!css/gohack.css',
                    '!css/gohack.min.css',
                ],
                dest: 'css/gohack.css',
            },

            hackjs: {
                src: [
                      'js/jquery.js',
                     /* 'js/classlist_polyfill.js',*/
                      'js/jquery.fittext.js',
                      'js/jquery.easing.min.js',
                      'js/jquery.easyPaginate.js',
                      'js/bootstrap.min.js',
                      'js/moment.min.js',
                      'js/wow.min.js',
                      'js/login.js',
                      'js/ideaPage.js',
                   /*   'js/idea.js',*/
                      'js/export.js',
                      'js/creative.js',
                      'js/countdown.js',
                      'js/cbpAnimatedHeader.js',
                      'js/classie.js',
                      '!js/gohack.js',
                      '!js/gohack.min.js',
                ],
                dest: 'js/gohack.js',
            },
        },

        connect: {
            dev: {
                options: {
                    middleware: function (connect, options) {
                        return [
                            // proxySnippet,
                            require('connect-livereload')(),
                            connect['static'](options.base),
                            connect.directory(options.base),
                        ];
                    },
                    port: '<%= dev_server_port %>',
                    base: '<%= working_base_folder %>',
                },
            },
        },

        open: {
            dev: {
                path: 'http://localhost:<%= dev_server_port %>',
            },
        },

    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-compass');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-imagemin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-connect-proxy');
    grunt.loadNpmTasks('grunt-open');
    grunt.loadNpmTasks('grunt-banner');
    grunt.loadNpmTasks('grunt-includes');

    // Default task
    // always run the bless after css concate & before cssmin, coz bless needs final css which produced by conate task
    grunt.registerTask('default', 'runs my tasks', function () {
        var tasks = [
            'clean:dev',
            'concat:hackcss',
            'concat:hackjs',
            'uglify:hackjs',
            'copy:dev',
            'cssmin:minify',
        ];

        // always use force when watching
        grunt.option('force', true);
        grunt.task.run(tasks);
    });

    var defaultTasks = ['default'];
    // Don't watch if the mode is prod
    if(grunt.option("mode") !== 'build') defaultTasks.push('watch');
    grunt.registerTask('start', defaultTasks);

    // run below task when Grunt.js changes been watched
    grunt.registerTask('watchdefault', 'runs my tasks', function () {
        var tasks = [
            'clean:dev',
        ];

        // always use force when watching
        grunt.option('force', true);
        grunt.task.run(tasks);
    });

};