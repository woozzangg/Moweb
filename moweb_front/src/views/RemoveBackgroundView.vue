<template>
    <div class="rm-background">
        <h1>This is an remove background page</h1>
        <video class="input_video"></video>
        <canvas class="output_canvas" width="1280px" height="720px"></canvas>
    </div>
</template>

<script>
import selfieSegmentation from "@mediapipe/selfie_segmentation";

export default {
    data: () => {
        return {
            selfieSegResult: null,
        }
    }

}
</script>

<style>

</style>