package com.example.aichat

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.*

object TTSsetting{
    private lateinit var tts: TextToSpeech
    private var ttsFinishedListener: (() -> Unit)? = null
    fun init(context: Context) {
        tts = TextToSpeech(context) {
            tts.language = Locale.KOREAN
            tts.setPitch(1.0f)
            tts.setSpeechRate(1.2f)
        }
    }

    fun setTTSFinishedListener(listener: () -> Unit) {
        ttsFinishedListener = listener
    }

    fun speakUp(response: String): Boolean {
        val utteranceId = UUID.randomUUID().toString()
        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {}

            override fun onDone(utteranceId: String) {
                ttsFinishedListener?.invoke()
                tts.setOnUtteranceProgressListener(null) // 리스너 해제
            }

            override fun onError(utteranceId: String) {}
        })
        tts.speak(response, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        return true

    }

    fun stop() {
        tts.stop()
        tts.shutdown()
    }
}