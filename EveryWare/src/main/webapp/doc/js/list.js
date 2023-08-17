/**
 * 
 */
$.listPageServer = function(cpage){
	vtype = $('#stype option:selected').val().trim();
	vword = $('#sword').val().trim();
	vDateFrom = $('#sDateFrom').val();
	vDateTo = $('#sDateTo').val();
	vStateType = $('#sStateType').val().trim();
	
	$.ajax({
		url: `${mypath}/documentList.do`,
		type: 'post',
		data: {
				"page" : cpage,
				"stype" : vtype,
				"sword" : vword,
				"sDateFrom" : vDateFrom,
				"sDateTo" : vDateTo,
				"sStateType" : vStateType,
				"menu" : mymenu
		},
		success: function(res){
			console.log(res);
			code = "";
			$.each(res.datas, function(i, v){
				if(mymenu == "received" || mymenu== "scrapped"){
					code += `<tr>
	    								<td>${v.rnum}</td>
							    		<td>${v.doc_no}</td>
							    		<td class="doc-title" id="${v.doc_id}">${v.doc_title}</td>
							    		<td class="doc-writer-name" id="${v.emp_id}">${v.emp_name}</td>
							    		<td>${v.aprv_date}</td>
							    	</tr>`
			/*	} else if(mymenu == "uploaded"){
					code += `<tr>
	    								<td>${v.rnum}</td>
							    		<td class="doc-title" id="${v.doc_id}">${v.doc_title}</td>
							    		<td>${v.doc_date}</td>
							    		<td>${v.doc_state}</td>
							    	</tr>`*/
				} else if(mymenu == "approval" || mymenu== "uploaded"){
					code += `<tr>
	    								<td>${v.rnum}</td>
	    								<td>${v.doc_no}</td>
							    		<td class="doc-title" id="${v.doc_id}">${v.doc_title}</td>
							    		<td class="doc-writer-name" id="${v.emp_id}">${v.emp_name}</td>
							    		<td>${v.doc_date}</td>
							    		<td>${v.aprv_date}</td>
							    		<td>${v.doc_state}</td>
							    	</tr>`
				}
			})
			
			$('#resultTable').html(code);
			
			// 페이지 처리
			pager = `<ul class="pagination">`;
			
			// 이전
			if(res.sp > 1){
				pager += `<li class="page-item"><a id="prev" href="#">&laquo;</a></li>`;
			} else{
				pager += `<li class="page-item"><a class="a-disabled" href="#">&nbsp;</a></li>`;
			}
			
			// 페이지 번호
			for(i=res.sp; i<=res.ep; i++){
				if(i == currentpage){
					pager += `<li class="page-item active"><a class="pageno active" href="#">${i}</a></li>`;
				}else{
					pager += `<li class="page-item"><a class="pageno" href="#">${i}</a></li>`;
				}
			}
			
			// 다음
			if(res.ep < res.tp){
				pager += `<li class="page-item"><a id="next" href="#">&raquo;</a></li>`;
			} else{
				pager += `<li class="page-item"><a class="a-disabled" href="#">&nbsp;</a></li>`;
			}
			pager += `</ul>`;
			
			$('#pagelist').html(pager);
			
		}, //success 끝
		error: function(xhr){
			alert('상태: ' + xhr.status);
		},
		dataType: 'json'
	})
}