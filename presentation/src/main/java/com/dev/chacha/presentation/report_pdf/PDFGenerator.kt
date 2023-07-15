/*
package com.dev.chacha.presentation.report_pdf

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.dev.chacha.presentation.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PDFGenerator {

    private val channelId = "PDF_DOWNLOAD_CHANNEL"
    private val notificationId = 123

    @RequiresApi(Build.VERSION_CODES.O)
    fun generatePDF(context: Context, transactionList: List<TransactionHistory>) {
        GlobalScope.launch(Dispatchers.IO) {
            // Create a new PDF document
            val pdfDocument = PdfDocument()

            try {
                // Create a page info with A4 dimensions
                val pageInfo = PdfDocument.PageInfo.Builder(A4_WIDTH, A4_HEIGHT, 1).create()

                // Start the first page
                val coverPage = pdfDocument.startPage(pageInfo)
                val coverCanvas = coverPage.canvas

                // Draw the cover image
                drawCoverImage(context, coverCanvas)

                // Draw the cover text
                drawCoverText(context, coverCanvas)

                // Finish the cover page
                pdfDocument.finishPage(coverPage)

                // Calculate the remaining height for the transaction pages
                val remainingHeight = A4_HEIGHT - (2 * MARGIN_SIZE)

                // Calculate the number of transactions that can fit on each page
                val transactionsPerPage = calculateTransactionsPerPage(remainingHeight)

                // Create a new page info for the transaction pages
                val transactionPageInfo = PdfDocument.PageInfo.Builder(A4_WIDTH, A4_HEIGHT, 1).create()

                // Iterate over the transaction list and create pages as needed
                var startIndex = 0
                while (startIndex < transactionList.size) {
                    val endIndex = startIndex + transactionsPerPage.coerceAtMost(transactionList.size - startIndex)

                    // Start a new transaction page
                    val transactionPage = pdfDocument.startPage(transactionPageInfo)
                    val transactionCanvas = transactionPage.canvas

                    // Draw the transaction rows
                    drawTransactionRows(context, transactionCanvas, transactionList.subList(startIndex, endIndex))

                    // Finish the transaction page
                    pdfDocument.finishPage(transactionPage)

                    // Update the start index for the next page
                    startIndex = endIndex
                }

                // Create a file to save the PDF
                val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val fileName = "TransactionHistory_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.pdf"
                val file = File(directory, fileName)

                // Write the PDF document to the file
                val fileOutputStream = FileOutputStream(file)
                pdfDocument.writeTo(fileOutputStream)
                fileOutputStream.close()

                // Show a download completed notification
                showDownloadNotification(context, file)

                // Show a toast message indicating successful PDF generation
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "PDF generated successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()

                // Show a toast message indicating an error occurred during PDF generation
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to generate PDF", Toast.LENGTH_SHORT).show()
                }
            } finally {
                // Close the PDF document
                pdfDocument.close()
            }
        }
    }

    private fun calculateTransactionsPerPage(remainingHeight: Float): Any {
        TODO("Not yet implemented")
    }

    private fun showDownloadNotification(context: Context, file: File) {
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("PDF Download")
            .setContentText("PDF Download Complete")
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, R.color.cyan))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("PDF downloaded successfully")
            )
            .setContentIntent(getPendingIntent(context, file))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }

    private fun getPendingIntent(context: Context, file: File): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        intent.setDataAndType(uri, "application/pdf")

        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }



    private fun drawCoverImage(context: Context, canvas: Canvas) {
        val coverBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.home)
        val scaledCoverBitmap = Bitmap.createScaledBitmap(coverBitmap, A4_WIDTH, A4_HEIGHT, true)
        canvas.drawBitmap(scaledCoverBitmap, 0f, 0f, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawCoverText(context: Context, canvas: Canvas) {
        val titlePaint = Paint()
        titlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        titlePaint.textSize = 40f
        titlePaint.color = Color.WHITE
        titlePaint.textAlign = Paint.Align.CENTER
        canvas.drawText("Transaction History", (A4_WIDTH / 2).toFloat(), 300f, titlePaint)

        val subtitlePaint = Paint()
        subtitlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        subtitlePaint.textSize = 30f
        subtitlePaint.color = Color.WHITE
        subtitlePaint.textAlign = Paint.Align.CENTER
        canvas.drawText("Generated on ${getCurrentDateTime()}", (A4_WIDTH / 2).toFloat(), 400f, subtitlePaint)
    }

    private fun drawTransactionRows(context: Context, canvas: Canvas, transactionList: List<TransactionHistory>) {
        val tableHeaderPaint = Paint()
        tableHeaderPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        tableHeaderPaint.textSize = 18f
        tableHeaderPaint.color = ContextCompat.getColor(context, R.color.black)
        tableHeaderPaint.textAlign = Paint.Align.CENTER

        val rowPaint = Paint()
        rowPaint.textSize = 16f
        rowPaint.color = ContextCompat.getColor(context, R.color.black)
        rowPaint.textAlign = Paint.Align.CENTER

        val columnWidths = calculateColumnWidths()

        val xOffset = MARGIN_SIZE + columnWidths[0] / 2

        // Draw table headers
        canvas.drawText("Transaction Detail", xOffset, TABLE_HEADER_Y, tableHeaderPaint)
        canvas.drawText("Recipient No", xOffset + columnWidths[0], TABLE_HEADER_Y, tableHeaderPaint)
        canvas.drawText("Date", xOffset + columnWidths[0] + columnWidths[1], TABLE_HEADER_Y, tableHeaderPaint)
        canvas.drawText("Money In", xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2], TABLE_HEADER_Y, tableHeaderPaint)
        canvas.drawText("Money Out", xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3], TABLE_HEADER_Y, tableHeaderPaint)
        canvas.drawText("Money Balance", xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3] + columnWidths[4], TABLE_HEADER_Y, tableHeaderPaint)


        var yOffset = TABLE_HEADER_Y + ROW_HEIGHT

        for (transaction in transactionList) {
            canvas.drawText(transaction.transactionDetail, xOffset, yOffset, rowPaint)
            canvas.drawText(transaction.recipientNo, xOffset + columnWidths[0], yOffset, rowPaint)
            canvas.drawText(transaction.date, xOffset + columnWidths[0] + columnWidths[1], yOffset, rowPaint)
            canvas.drawText(transaction.moneyIn.toString(), xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2], yOffset, rowPaint)
            canvas.drawText(transaction.moneyOut.toString(), xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3], yOffset, rowPaint)
            canvas.drawText(transaction.moneyBalance.toString(), xOffset + columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3] + columnWidths[4], yOffset, rowPaint)

            yOffset += ROW_HEIGHT
        }
    }

    private fun calculateColumnWidths(): List<Float> {
        val MARGIN_SIZE = 20f
        val totalWidth = A4_WIDTH - (MARGIN_SIZE * 2)
        val columnWidths = mutableListOf<Float>()

        val transactionDetailWidth = totalWidth * 0.4f
        val recipientNoWidth = totalWidth * 0.15f
        val dateWidth = totalWidth * 0.15f
        val moneyInWidth = totalWidth * 0.1f
        val moneyOutWidth = totalWidth * 0.1f
        val moneyBalanceWidth = totalWidth * 0.1f

        columnWidths.add(transactionDetailWidth)
        columnWidths.add(recipientNoWidth)
        columnWidths.add(dateWidth)
        columnWidths.add(moneyInWidth)
        columnWidths.add(moneyOutWidth)
        columnWidths.add(moneyBalanceWidth)

        return columnWidths
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDateTime.format(formatter)
    }

    companion object {
        private const val A4_WIDTH = 595
        private const val A4_HEIGHT = 842
        private const val MARGIN_SIZE = 595f
        private const val TABLE_HEADER_Y = 842f
        private const val ROW_HEIGHT = 842f
    }
}

data class TransactionHistory(
    val transactionDetail: String,
    val recipientNo: String,
    val date: String,
    val moneyIn: Double,
    val moneyOut: Double,
    val moneyBalance: Double
)

val transactionList = listOf(
    TransactionHistory(
        transactionDetail = "Payment for Mpesa no",
        recipientNo = "REIUYOBHY",
        date = "12/20/2023",
        moneyIn = 200.0,
        moneyOut = 300.0,
        moneyBalance = 3000.0
    ),
    TransactionHistory("Payment received", "123456789", "2023-04-01", 100.0, 0.0, 100.0),
    TransactionHistory("Transfer to savings", "987654321", "2023-04-02", 0.0, 50.0, 50.0),
    TransactionHistory("Withdrawal", "555555555", "2023-04-03", 0.0, 20.0, 30.0),
    TransactionHistory("Payment received", "123456789", "2023-04-04", 50.0, 0.0, 80.0),
    TransactionHistory(
        transactionDetail = "Payment for Mpesa no",
        recipientNo = "REIUYOBHY",
        date = "12/20/2023",
        moneyIn = 200.0,
        moneyOut = 300.0,
        moneyBalance = 3000.0
    ),
    TransactionHistory(
        transactionDetail = "Payment for Mpesa no",
        recipientNo = "REIUYOBHY",
        date = "12/20/2023",
        moneyIn = 200.0,
        moneyOut = 300.0,
        moneyBalance = 3000.0
    ),
)
*/
