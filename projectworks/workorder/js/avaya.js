//Set Your own APIKEY here for using static map from Google or Baidu.
var location1_MAP_GOOGLE_APIKEY = "";
var location1_MAP_BAIDU_APIKEY = "";
var location1_MAP_TYPE_GOOGLE = "GOOGLE";
var location1_MAP_TYPE_BAIDU = "BAIDU";
var location1_GOOGLE_MAP_PARAMS = {"url":"//maps.googleapis.com/maps/api/staticmap?center=", "zoom":"14", "size":"400x300", "markerStyle":"color:red%7C"};
var location1_BAIDU_MAP_PARAMS = {"url":"//api.map.baidu.com/staticimage/v2?center=", "zoom":"16", "width":"400", "height":"300", "markerStyle":"-1,http://api.map.baidu.com/images/marker_red.png"};
//Fill in the following parameter for 3rd party map system
var location1_MAP_TYPE_OTHER = "OTHER";
var location1_MAP_OTHER_APIKEY = "";
var location1_OTHER_MAP_PARAMS = {"url":"//<domain>/<api url>", "zoom":"14", "size":"400x300", "markerStyle":"color:red%7C"};
//Example for mapquest -
//var location1_OTHER_MAP_PARAMS = {"url":"//www.mapquestapi.com/staticmap/v4/getmap?", "zoom":"14", "size":"400,300", "markerStyle":"&type=map&imagetype=jpeg&pois=orange-a,"};

function location1(elementName, mapType){
	this.elementName = elementName;
	this.mapType = mapType;
	var parent = this;
	var getURL = function(){};
	
	if (mapType == location1_MAP_TYPE_GOOGLE){
		getURL = function(latlon){
			var img_URL = window.location.protocol + location1_GOOGLE_MAP_PARAMS.url + latlon + "&" + location1_MAP_GOOGLE_APIKEY + "&zoom=" + location1_GOOGLE_MAP_PARAMS.zoom + "&size=" +
				location1_GOOGLE_MAP_PARAMS.size + "&sensor=false&markers=" + location1_GOOGLE_MAP_PARAMS.markerStyle + latlon;
			return img_URL;
		}
	}else if(mapType == location1_MAP_TYPE_BAIDU){
		getURL = function(latlon){		
			var img_URL = window.location.protocol + location1_BAIDU_MAP_PARAMS.url + latlon + "&" + location1_MAP_BAIDU_APIKEY + "&width=" + location1_BAIDU_MAP_PARAMS.width + 
				"&height=" + location1_BAIDU_MAP_PARAMS.height + "&zoom=" + location1_BAIDU_MAP_PARAMS.zoom + "&markers=" + latlon +
				"&markerStyles=" + location1_BAIDU_MAP_PARAMS.markerStyle;
			return img_URL;
		}
	}else{
		//For other map type, customers would have to customize the following code to make sure the static map url is generated according to 3rd party API docs.
		//Example for mapquest
		/*getURL = function(latlon){
			var img_URL = window.location.protocol + location1_OTHER_MAP_PARAMS.url + location1_MAP_OTHER_APIKEY + "&zoom=" + location1_OTHER_MAP_PARAMS.zoom + "&size=" +
			location1_OTHER_MAP_PARAMS.size + "&center=" + latlon + location1_OTHER_MAP_PARAMS.markerStyle + latlon;
			return img_URL;
		}*/
		getURL = function(latlon){
			var img_URL = window.location.protocol + location1_OTHER_MAP_PARAMS.url + latlon + "&zoom=" + location1_OTHER_MAP_PARAMS.zoom + "&size=" +
			location1_OTHER_MAP_PARAMS.size + "&sensor=false&markers=" + location1_OTHER_MAP_PARAMS.markerStyle + latlon;
			return img_URL;
		}
	}
	
	this.getLocation = function()
	{
	  if (navigator.geolocation)
	    {	  
		  navigator.geolocation.getCurrentPosition(this.showPosition, this.showError);
	    }
	};
	
	this.showPosition = function(position)
	  {
		var latlon=position.coords.latitude+","+position.coords.longitude;
		if (mapType == location1_MAP_TYPE_BAIDU){
			var latlon=position.coords.longitude+","+position.coords.latitude;
		}
		  var checked = document.getElementById("checkbox-"+parent.elementName).checked;
		  if (checked){
		  	document.getElementById("mapholder-"+parent.elementName).innerHTML="<img style='max-width:100%;max-height:100%' src='"+getURL(latlon)+"'>";
		  	$('#'+parent.elementName).val(latlon);
		  }else{
			  document.getElementById("mapholder-"+parent.elementName).innerHTML="";
			  	$('#'+parent.elementName).val("");
		  } 
	  };
	
	this.showError = function(error)
	  {
	  	alert("can not get location info - " + error.code);
	  };
}

function Picture(canvasElem, videoElem, btnStart, btnPhoto, showElem){
	 this.canvas = canvasElem;
     this.context = canvasElem.getContext("2d");
     this.video = videoElem;
     this.btnStart = btnStart;      
     this.btnPhoto = btnPhoto;
     this.show = showElem
     
     var parent = this;
     
     this.init = function(){
    	 navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia||navigator.mozGetUserMedia || navigator.msGetUserMedia;
    	 btnStart.addEventListener("click", function() {
    	        var localMediaStream;

    	        if (navigator.getUserMedia) {
    	        	var videoObj = {
    	        	         video: true,
    	        	         audio: true
    	        	     };
    	            navigator.getUserMedia(videoObj, function(stream) {
    	                video.src = (navigator.webkitGetUserMedia) ? window.webkitURL.createObjectURL(stream) : window.URL.createObjectURL(stream);
    	                localMediaStream = stream;

    	            }, function(error) {
    	                console.error("Video capture error: ", error.code);
    	            });

    	            btnPhoto.addEventListener("click", function() {
    	                parent.context.drawImage(video, 0, 0, 320, 240);
    	                var base64dataUrl = canvas.toDataURL('image/png');    	               
    	                parent.show.value = base64dataUrl;
    	            });
    	        }
    	    });
     }
}

function Audio(previewElem, btnRecord, showElem){
	var ORIGINAL_DOC_TITLE = document.title;
	this.preview = previewElem;        
    this.btnRecord = btnRecord;
    this.show = showElem

    var parent = this;
    var Context = window.AudioContext || window.webkitAudioContext;
	var context = new Context();
	var mediaStream;
	var rec;
	
    this.init = function(){
    	window.URL = window.URL || window.webkitURL;
    	navigator.getUserMedia = navigator.getUserMedia ||
   	 	navigator.webkitGetUserMedia || navigator.mozGetUserMedia ||
   	 	navigator.msGetUserMedia;    	
    }
    
    btnRecord.addEventListener("click", doAction);
    
    function doAction(){
    	if (btnRecord.innerText == "Record"){
    		btnRecord.innerText = "Stop";
    		record();    		
    	}else{
    		btnRecord.innerText = "Record";
    		stop();
    	}
    	
    };    
    
    function record() {
    	navigator.getUserMedia({audio: true}, function(localMediaStream){
    		document.title = 'Recording...';
    		mediaStream = localMediaStream;
    		var mediaStreamSource = context.createMediaStreamSource(localMediaStream);
    	    rec = new Recorder(mediaStreamSource, {
    	      workerPath: 'recorderWorker.js'
    	    });

    	    rec.record();
    	  }, function(err){
    	    console.log('Not supported');
    	  });
    }

    function stop() {
    	  mediaStream.stop();
    	  rec.stop();
    	  document.title = ORIGINAL_DOC_TITLE;

    	  rec.exportWAV(function(e){
    		  rec.clear();
	    	  //Recorder.forceDownload(e, "test.wav");
    		  var url = window.URL.createObjectURL(e);
	    	  if (parent.show != null){
	    		  parent.show.value = url;
	    	  }
	    	  preview.src = url;
    	  });
    	}
}

function Video(previewElem, videoElem, btnStart, btnRecord, showElem){
	var ORIGINAL_DOC_TITLE = document.title;
	this.preview = previewElem;  
    this.video = videoElem;
    this.btnStart = btnStart;      
    this.btnRecord = btnRecord;
    this.show = showElem
    
    var rafId = null;
    var startTime = null;
    var endTime = null;
    var frames = [];
    var canvas = document.createElement('canvas'); 
    
    var parent = this;
    
    this.init = function(){
    window.URL = window.URL || window.webkitURL;
    window.requestAnimationFrame = window.requestAnimationFrame ||
    	window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame ||
    	window.msRequestAnimationFrame || window.oRequestAnimationFrame;
    window.cancelAnimationFrame = window.cancelAnimationFrame ||
    	window.webkitCancelAnimationFrame || window.mozCancelAnimationFrame ||
    	window.msCancelAnimationFrame || window.oCancelAnimationFrame;
    	
   	 navigator.getUserMedia = navigator.getUserMedia ||
   	 	navigator.webkitGetUserMedia || navigator.mozGetUserMedia ||
   	 	navigator.msGetUserMedia;
   	 
   	canvas.width = video.width;
    canvas.height = video.height;
   	 
   	 btnStart.addEventListener("click", function() {
   	        var localMediaStream;

   	        if (navigator.getUserMedia) {
   	        	var videoObj = {
   	        	         video: true,
   	        	         audio: true
   	        	     };
   	            navigator.getUserMedia(videoObj, function(stream) {
   	                video.src = (navigator.webkitGetUserMedia) ? window.webkitURL.createObjectURL(stream) : stream;
   	                localMediaStream = stream;

   	            }, function(error) {
   	                console.error("Video capture error: ", error.code);
   	            });   	       
   	        }
   	    });
    }
    
    btnRecord.addEventListener("click", doAction);
    
    function doAction(){
    	if (btnRecord.innerText == "Record"){
    		btnRecord.innerText = "Stop";
    		record();    		
    	}else{
    		btnRecord.innerText = "Record";
    		stop();
    	}
    	
    };
    
    function record() {
    	var ctx = canvas.getContext('2d');
    	var CANVAS_HEIGHT = canvas.height;
    	var CANVAS_WIDTH = canvas.width;

    	frames = [];
    	startTime = Date.now();    	

		function drawVideoFrame_(time) {
			rafId = window.requestAnimationFrame(drawVideoFrame_);
		    ctx.drawImage(video, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		    document.title = 'Recording...' + Math.round((Date.now() - startTime) / 1000) + 's';
		    var url = canvas.toDataURL('image/webp', 1);
		    frames.push(url);
		};

		rafId = window.requestAnimationFrame(drawVideoFrame_);
    };
    
    function stop() {
    	window.cancelAnimationFrame(rafId);
    	endTime = Date.now();
    	document.title = ORIGINAL_DOC_TITLE;

    	console.log('frames captured: ' + frames.length + ' => ' +
    	((endTime - startTime) / 1000) + 's video');

    	window.URL.revokeObjectURL(preview.src);
    	var webmBlob = Whammy.fromImageArray(frames, 1000 / 60);
        var url = window.URL.createObjectURL(webmBlob);     
        /*var reader = new window.FileReader();
        reader.readAsDataURL(webmBlob); 
        reader.onloadend = function() {
                       base64data = reader.result;                
                       //console.log(base64data );
                       preview.src = base64data;
                       
         }*/
    	if (parent.show != null){
     	   parent.show.value = url;
        }
        preview.src = url;
    };
}