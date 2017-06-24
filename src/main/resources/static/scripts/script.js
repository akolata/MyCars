(function ($) {
    $(document).ready(function () {

        $('.parallax').parallax();
        $('.dropdown-button').dropdown();
        $('select').material_select();

        $('#navigation a').each(function () {
            var location = window.location.href;
            var link = this.href;
            if(location == link) {
                $(this).parent().addClass('active');
            }
        });

        $("#add-car-btn").on("click",function (e) {

            var content = $("#add-car-div");

            if(content.is(":hidden")){
                content.show("slow");
            }else{
                content.hide("slow");
            }

        });

    });

})(jQuery);