/*
 * Main file for editor application
 *
 * Starts up the editor and initialises all its modules to be ready for user
 * interations
 */
(function () {
    "use strict";

    /*
     * Make sure requirejs doesn't cache any files
     *
     * This cannot be put into config, otherwise the config itself might get
     * cached and this line will make no effect
     */
    require.config({ urlArgs: "bust=" + (new Date()).getTime() });

    // require config and continue initialisation
    requirejs(['config'], function () {
        // load template loader first, so we can show nice loading animation
        requirejs(['util/simple-template'], function (template) {
            // render the main page (will start loading animation)
            template.render('#page', 'editor').then(function () {
                // load bootstrap which will initialise on just rendered page
                // and delegate the rest of the initialisation to main()
                requirejs(['bootstrap'], main);
            });
        })
    });

    /*
     * Loads all the required modules and initialises the page
     */
    function main() {
        // load auth and navigation controllers
        var controllers = [ 'share/auth', 'share/nav' ];
        require(controllers, function (auth, nav) {
            // try to login
            auth.tryLogin().then(function () {
                // automatically route to the landing page or the hashed page
                nav.autoHash();
            }).fail(function () {
                // go to index page which asks to login
                nav.index();
            });
        });
    }
})();
