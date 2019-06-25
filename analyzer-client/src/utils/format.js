
export function statusCardFormat(data,property,status,header){        //传入数组和状态,包装为 statusCard 组件需要的数据,额外传入标题
    if(arguments.length < 3){
        throw new Error("参数数量不够")
    }
    let ret ={
      header:arguments.length === 3?status:header,
      data:data.filter(item=>{
              return item[property] === status
      })
    }
    return ret
}