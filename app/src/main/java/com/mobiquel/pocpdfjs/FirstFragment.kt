package com.mobiquel.pocpdfjs

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.mobiquel.pocpdfjs.databinding.FragmentFirstBinding
import java.io.File


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        /*val toPath = File(requireContext().getFilesDir(), "pdf_v2")

        copyAssetFolder(requireContext().assets, "pdf_v2", toPath.path)

        //  }


        //  }
        val indexPage = File(toPath, "index.html")
        binding.webView.setPadding(0, 0, 0, 0)
        binding.webView.setInitialScale(1)
        val webSettings: WebSettings = binding.webView.getSettings()
        webSettings.javaScriptEnabled = true
        binding.webView.getSettings().setLoadWithOverviewMode(true)
        binding.webView.getSettings().setUseWideViewPort(true)
        binding.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        binding.webView.setScrollbarFadingEnabled(false)
        binding.webView.setWebChromeClient(WebChromeClient())
        binding.webView.setWebViewClient(object : WebViewClient() {
            private var doIt = false
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                doIt = true
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                if (doIt) {
                    loadPdf(R.raw.alphatrans)
                    doIt = false
                }
            }
        })
        binding.webView.loadUrl("file:///" + indexPage.absolutePath)
*/
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "Shamshad.pdf"
        )
       /* Log.e("FILE PATH", "== " + file.absolutePath)
        val path =
            Uri.parse(Environment.getExternalStorageDirectory().toString() + "/data/test.pdf")
        Log.e("FILE PATH", "== " + path.toString())
*/
        val settings: WebSettings = binding.webView.getSettings()
        settings.javaScriptEnabled = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.builtInZoomControls = true
        //settings.allowFileAccessFromFileURLs = true
        settings.allowFileAccess = true
        binding.webView.setWebChromeClient(WebChromeClient())

        binding.webView.loadUrl(
            "file:///android_asset/pdf/web/viewer.html?file=" + requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM).toString() + File.separator + "PDFVIEW" + "/alphatrans.pdf"
        )
        check_folder(requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM).toString() + File.separator + "PDFVIEW")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        //binding.webView.loadUrl("javascript:window.location.reload( true )");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun check_folder(path: String?) {
        //  Log.e("PATH", "=== " + path);
        val file = File(path)
        if (!file.exists()) {
            file.mkdir()
        }
    }

}