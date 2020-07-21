/**
 * Add some the message method to JQuery Mobile to show error messages
 */
(function (jQuery)
{
    var methods = {
        init: function (options)
        {
            options = jQuery.extend({}, options);
            return this.each(function ()
            {
                var $this = jQuery(this);
                var data = $this.data("options");
                if (!data)
                {
                	var messageText = messageText = $this.html();
                    var messageHtml = "<div class='mycontainer' data-collapsed='false' data-role='collapsible' data-theme='a' data-content-theme='a'>";
                    if(typeof $.validator.messages.apperror === 'undefined'){
                    	 messageHtml += "<h3>Error</h3>";
                    }else{
                    	messageHtml += "<h3>" + $.validator.messages.apperror + "</h3>";
                    }
                    messageHtml += "<p><span style='color:red' class='message-text'>" + messageText + "</span>";
                    if(typeof $.validator.messages.apperror_dismiss === 'undefined'){
                    	messageHtml += "<a class='message-dismiss' href='#'>Dismiss</a>";
                    }else{
                    	messageHtml += "<a class='message-dismiss' href='#'>" + $.validator.messages.apperror_dismiss +"</a>";
                    }                    
                    messageHtml += "</p></div>";
                    $this.html(messageHtml);
                    $this.find(".mycontainer").collapsible();               
                    jQuery(".message-dismiss", $this).click(function ()
                    {
                        $this.hide('normal');
                        return false;
                    });                   
                    $this.show();

                    $this.data("options", options);
                }
            });
        },
        options: function (options)
        {
            return this.each(function ()
            {
                var $this = jQuery(this);
                var currentOptions = $this.data("options") || {};
                options = jQuery.extend({}, options);
                $this.avayaMessage("destroy").avayaMessage("init", options);
            });
        },
        show: function ()
        {
            jQuery(this).show();
        },
        hide: function ()
        {
            jQuery(this).hide();
        },
        destroy: function ()
        {
            return this.each(function ()
            {
                var $this = jQuery(this);
                var data = $this.data("options");
                jQuery(".mycontainer", $this).remove();                
                $this.removeData("options");
            });
        }
    };

    jQuery.fn.avayaMessage = function (method)
    {
        if (methods[method])
        {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        }
        else if (typeof (method) === 'object' || !method)
        {
            return methods.init.apply(this, arguments);
        }
        else
        {
            jQuery.error("Method " + method + " does not exist on jQuery.avayamessage");
        }
    };

})(jQuery);
