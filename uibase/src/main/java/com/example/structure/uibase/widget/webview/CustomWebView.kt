package com.example.structure.uibase.widget.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class CustomWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr), LifecycleEventObserver {

    private var _webClientListener: WebClientListener? = null
    private var _webChromeClientListener: WebChromeClientListener? = null

    private val _additionalHeaders: HashMap<String, String> by lazy {
        hashMapOf(HEADER_NAME to HEADER_VALUE)
    }

    var buttonJavascriptClickListener: ((idOrClass: String) -> Unit)? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUp()
    }

    private fun webViewOnResume() {
        onResume()
    }

    override fun onResume() {
        super.onResume()
        resumeTimers()
    }

    private fun webViewOnPause() {
        onPause()
    }

    override fun onPause() {
        pauseTimers()
        super.onPause()
    }

    private fun webViewOnDestroy() {
        destroy()
    }

    private fun setUp() {
        setUpWebViewSettings()
        setUpLayerType()
        registerWebClient()
        registerButtonCallbackJs()
    }

    private fun registerWebClient() {
        webViewClient = customWebViewClient
        webChromeClient = customChromeClient
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebViewSettings() {
        this.settings.apply {
            builtInZoomControls = true
            javaScriptEnabled = true
            domStorageEnabled = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            cacheMode = WebSettings.LOAD_DEFAULT
            defaultTextEncodingName = "UTF-8"
            loadWithOverviewMode = true
        }
        this.isFocusableInTouchMode = true
        this.isFocusable = true
    }

    private fun setUpLayerType() {
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            // chromium, enable hardware acceleration
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
    }

    override fun loadUrl(url: String) {
        loadUrl(url, _additionalHeaders)
    }

    private fun registerButtonCallbackJs() {
        addJavascriptInterface(ButtonJavascriptListener(), NAME_CALLBACK_BTN_JS)
    }

    fun setWebClientListener(webClientListener: WebClientListener) {
        this._webClientListener = webClientListener
    }

    fun setWebChromeClientListener(webChromeClientListener: WebChromeClientListener) {
        this._webChromeClientListener = webChromeClientListener
    }

    private val customChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            _webChromeClientListener?.onProgressChanged(view, newProgress)
        }

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            return true
        }
    }

    private val customWebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            _webClientListener?.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            evaluateJavascript(addOnClickBtnCallBackJs(), null)
            _webClientListener?.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return _webClientListener?.shouldOverrideUrlLoading(view, request) ?: false
        }

        override fun onRenderProcessGone(
            view: WebView?,
            detail: RenderProcessGoneDetail?
        ): Boolean {
            return true
        }
    }

    private fun addOnClickBtnCallBackJs(): String {
        var js = "javascript:"
        js += "function onClickBtn(event){" +
            "if(event.target.className == null){$NAME_CALLBACK_BTN_JS.onClickBtn(event.target.id)}" +
            "else{$NAME_CALLBACK_BTN_JS.onClickBtn(event.target.className)}}"
        js += "document.addEventListener(\"click\",onClickBtn,true);"

        return js
    }

    override fun destroy() {
        super.destroy()
        _webChromeClientListener = null
        _webClientListener = null
        buttonJavascriptClickListener = null
    }

    private inner class ButtonJavascriptListener {
        @Suppress("unused")
        @JavascriptInterface
        fun onClickBtn(idOrClass: String) {
            buttonJavascriptClickListener?.invoke(idOrClass)
        }
    }

    companion object {

        private const val TAG = "SimpleWebView"
        private const val NAME_CALLBACK_BTN_JS = "btnJsCallBack"
        private const val HEADER_NAME = "x-channel"
        private const val HEADER_VALUE = "ph-universal-android"
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {
                webViewOnPause()
            }
            Lifecycle.Event.ON_RESUME -> {
                webViewOnResume()
            }
            Lifecycle.Event.ON_DESTROY -> {
                webViewOnDestroy()
            }
            else -> {}
        }
    }
}

interface WebClientListener {
    fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)

    fun onPageFinished(view: WebView?, url: String?)

    fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean = false
}

interface WebChromeClientListener {
    fun onProgressChanged(view: WebView, newProgress: Int)
}
