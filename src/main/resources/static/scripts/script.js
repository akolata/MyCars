(function ($) {
    $(document).ready(function () {

        $('.parallax').parallax();




        $('#navigation a').each(function () {
            var location = window.location.href;
            var link = this.href;
            if(location == link) {
                $(this).parent().addClass('active');
            }
        });


    });
})(jQuery);