import moment from 'moment'

export function dateFormat (row, column) {
	//时间格式化  
    let date = row[column.property];  
    if (date == undefined) {  
      return "";  
    }  
    return moment(date).format("YYYY-MM-DD HH:mm:ss");
}

export function dateFormatSimp (row, column) {
	//时间格式化  
    let date = row[column.property];  
    if (date == undefined) {  
      return "";  
    }  
    return moment(date).format("YYYY-MM-DD");  
}

export function dateFormats (val) {
	 return moment(val).format("YYYY-MM-DD HH:mm:ss");
}
export function dateFormatSimps (val) {
	 return moment(val).format("YYYY-MM-DD");
}
