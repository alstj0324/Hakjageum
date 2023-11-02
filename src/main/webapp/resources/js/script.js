(function ($) {

  "use strict";

  $(document).ready(function () {
    $('.navbar').on('click', '.search-toggle', function (e) {
      var selector = $(this).data('selector');

      $(selector).toggleClass('show').find('.search-input').focus();
      $(this).toggleClass('active');

      e.preventDefault();
    });

    // close when click off of container
    $(document).on('click touchstart', function (e) {
      if (!$(e.target).is('.search-toggle, .search-toggle *, .navbar, .navbar *')) {
        $('.search-toggle').removeClass('active');
        $('.navbar').removeClass('show');
      }
    });
	
    // Payment method
    $('input[type="radio"]').click(function () {
      var inputValue = $(this).attr("value");
      var targetBox = $("." + inputValue);
      $(".payment-box").not(targetBox).hide();
      $(targetBox).show();
    });
  });
})(jQuery);