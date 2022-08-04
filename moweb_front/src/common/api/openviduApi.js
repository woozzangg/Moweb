import axios from "axios";
import { OpenVidu } from 'openvidu-browser';

const OV = new OpenVidu();
let session = null;
let token = null;

const axiosInstance = axios.create({
    baseURL: 'https://localhost/openvidu-session/'
});

const openviduRoom = {
    getToken: async function(roomNo, callback){
        sessionName = roomNo; // Video-call chosen by the user
        const response = await axiosInstance.post("/get-token", {sessionName: roomNo})

        console.warn('Request of TOKEN gone WELL (TOKEN:' + token + ')');
        token = response[0]; // Get token from response

        return token;
    },
    joinSession: async function(roomNo, myName){

        token = await getToken(roomNo);
    
        // --- 1) Get an OpenVidu object ---
    
        OV = new OpenVidu();
    
        // --- 2) Init a session ---
    
        session = OV.initSession();
    
        // --- 3) Specify the actions when events take place in the session ---
    
        // On every new Stream received...
        session.on('streamCreated', (event) => {
    
            // Subscribe to the Stream to receive it
            // HTML video will be appended to element with 'video-container' id
            var subscriber = session.subscribe(event.stream, 'video-container');
    
            // When the HTML video has been appended to DOM...
            subscriber.on('videoElementCreated', (event) => {
    
                // Add a new HTML element for the user's name and nickname over its video
                appendUserData(event.element, subscriber.stream.connection);
            });
        });
    
        // On every Stream destroyed...
        session.on('streamDestroyed', (event) => {
            // Delete the HTML element with the user's name and nickname
            removeUserData(event.stream.connection);
        });
    
        // On every asynchronous exception...
        session.on('exception', (exception) => {
            console.warn(exception);
        });
    
        session.connect(token, { clientData: myName })
        .then(() => {
    
            // --- 5) Set page layout for active call ---
    
    
            // --- 6) Get your own camera stream ---
    
            let publisher = OV.initPublisher('video-container', {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: '640x480',  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: true       	// Whether to mirror your local video or not
            });
            
            // --- 7) Specify the actions when events take place in our publisher ---
    
            // When our HTML video has been added to DOM...
            publisher.on('videoElementCreated', (event) => {
                // Init the main video with ours and append our data
            });
    
            // --- 8) Publish your stream ---
    
            session.publish(publisher);
        })
        .catch(error => {
            console.warn('There was an error connecting to the session:', error.code, error.message);
        });
    },
    leaveSession: function(){
        session.disconnect();
        session = null;
    }
}

export default openviduRoom;