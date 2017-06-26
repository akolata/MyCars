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

        $("#add-car-btn").on("click",function () {

            var content = $("#add-car-div");

            if(content.is(":hidden")){
                content.show("slow");
            }else{
                content.hide("slow");
            }

        });

        $("#fuel-cost").change((function () {

            var input=$(this),
                cost = input.val(),
                distance = $("#distance").html(),
                result = $("#total-distance-cost");

            var isNumber = /^[0-9.]+$/.test(cost);

            if(isNumber){
                if(cost > 0){
                    result.val(cost * distance);
                }
            }

        }));

        $("#history-notes .collection-item").on("click",function () {

            var target = $(this),
                listItem = target.find("p");

            console.log(target);
            console.log(listItem);

            if(listItem.is(":hidden")){
                listItem.show("slow");
            }else{
                listItem.hide("slow");
            }
        });

    });

})(jQuery);