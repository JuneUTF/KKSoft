
const showForm = document.getElementById("showForm");
let booleanID = 0;
let showID = false;
function deleteemployee(id, status, tableName) {
  if (booleanID != ("D" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormDelete(id, status, tableName)
    }, 300);
  } else {
    if (!showID) {
      hiddenFormHTML()
    } else {
      hiddenFormHTML()
    } 
  }
}
function restoreemployee(id, status, tableName) {
  if (booleanID != ("R" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormreStore(id, status, tableName)
    }, 300);
  } else {
    if (!showID) {
      showFormreStore(id, status, tableName)
    } else {
      hiddenFormHTML()
    }
  }
}

function editemployee(id, text, status,end,start, tableName) {
  if (booleanID != ("E" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormEdit(id, text, status,end,start, tableName)
    }, 300);
  } else {
    if (!showID) {
      showFormEdit(id, text, status,end,start, tableName)
    } else {
      hiddenFormHTML()
    }
  }
}
function showFormDelete(id, status, tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "employee" ? "社員情報" : "担当者"}の削除</h3></div><div class="modal-body">ID: ${id}、${status}の削除に解除のＯＫですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <form action="/kk/office/employee/delete?id=${id}" method="post"><button type="submit" class="btn btn-danger">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "D" + id;
}
function showFormreStore(id, status, tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "employee" ? "employee" : "担当者"}の復元</h3></div><div class="modal-body">ID: ${id}、 ${status}の使用中に復元のＯＫですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <form action="/kk/office/employee/restore?id=${id}" method="post"><button type="submit" class="btn btn-primary">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "R" + id;
}
function showFormEdit(id, nameid,status,end,start, tableName) {
  let showEnd =`<label for="textEdit" class="form-label mt-2">現場終了日：</label> <span id="cleanEnd" class="btn btn-secondary btn-sm" >クリア</span>
  <input type="date" class="form-control"  name="plan_end" id="plan_end" value="${end}"></input>`
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">社員情報の編集</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/office/employee/edit?id=${id}" method="post">
  <div class="modal-body">
	<label for="textEdit" class="form-label">名前：</label>
	<input type="text" class="form-control"  name="nameid" value="${nameid}" required>
	<label for="textEdit" class="form-label mt-2">状況：</label>
	<select id="status" name = "status" class="form-select">
	<option value="${status}">${status}</option>
	<option value="現場">現場</option>
	<option value="営業">営業</option>
	<option value="帰国">帰国</option>
	<option value="休職">休職</option>
	<option value="退職">退職</option>
	</select>
  	${status == "現場"? showEnd : "<span id='showinput'></span>"}
  	<label for="textEdit" class="form-label mt-2">稼動開始：</label><span id="cleanStart" class="btn btn-secondary btn-sm" >即日</span>
  	<input type="date" class="form-control"  name="date_start" id="date_start" value="${start}">
  </div> 
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true; 
  booleanID = "E" + id;
  let statusID = document.getElementById("status");
  document.getElementById('cleanStart').addEventListener('click',()=>{document.getElementById('date_start').value = null})
  statusID.addEventListener('change',()=>{
    if(statusID.value =="現場"){
      document.getElementById("showinput").innerHTML =showEnd;
      document.getElementById('cleanEnd').addEventListener('click',()=>{document.getElementById('plan_end').value = null})
    }else{
    	document.getElementById("showinput").innerHTML ="";
    }
  })
}
function registeremployee(tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">内容の新規登録</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/office/employee/reg/" method="post">
  <div class="modal-body">
	<input type="text" class="form-control" id="nameid" name="nameid"  placeholder="内容を入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
}
function hiddenFormHTML() {
  showForm.style.animationName = "deleleForm";
  showID = false;
  booleanID = 0;
}

