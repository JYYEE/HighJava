package everyware.doc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String filePath = request.getParameter("path");
		String fileName = request.getParameter("name");
		
		File downFile = new File(filePath);
		
		if (downFile.exists()) {
			response.setContentType("application/octet-stream; charset=utf-8");

			String headerKey = "content-disposition";

			String headerValue = "attachment; " + getEncodedFileName(request, fileName);

			response.setHeader(headerKey, headerValue);

			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용 스트림 객체 생성: response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());

				// 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));

				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();

			} catch (Exception e) {
				System.err.println("입출력 오류: " + e.getMessage());
			} finally {
				if(bout!=null) try { bout.close(); } catch(IOException e) {} 
				if(bin!=null) try { bin.close(); } catch(IOException e) {} 
			}

		} else {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<h3>파일이 존재하지 않습니다.</h3>");
		}
		
	}
	
	// 다운로드 파일명에 한글이 포함되었을 때 한글이 깨지는 것을 방지하는 메서드
	private String getEncodedFileName(HttpServletRequest request, String filename) {
		String encodedFilename = "";

		String userAgent = request.getHeader("user-agent");

		try {
			// MSIE 10 버전 이하의 웹브라우저 찾기
			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				encodedFilename = "filename=\"" + URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "\\ ") + "\"";
			} else {
				encodedFilename = "filename*=UTF-8''" + URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
			}
		} catch (Exception e) {
			throw new RuntimeException("지원하지 않는 웹브라우저입니다.");
		}

		return encodedFilename;
	}

}
