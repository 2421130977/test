public class App {
    public static void main(String[] args) {
        // 这里填写表达式
        String expression = "1*1/1+4+7-3*5-8-6+4+23";
        System.out.println("表达式计算结果\n"+compute(expression,0,expression.length()-1)+"="+expression);
    }

    public static Integer compute(String expression,int start,int end){
        Integer result=null;
        String currentOp=null;
        Integer numberStart=start;
        Integer numberEnd=start;
        boolean numberCC=false;
        while (numberEnd<=end){
            if (expression.charAt(numberEnd)=='*'||expression.charAt(numberEnd)=='/'){
                numberCC=true;
            }
            if (expression.charAt(numberEnd)=='+'||expression.charAt(numberEnd)=='-'){
                if (result==null){
                    if (numberCC){
                        result=computeCC(expression,numberStart,numberEnd-1);
                    }else {
                        result=Integer.valueOf(expression.substring(numberStart,numberEnd));
                    }
                }
                if ("+".equals(currentOp)){
                    result=result+(numberCC?computeCC(expression,numberStart,numberEnd-1):Integer.valueOf(expression.substring(numberStart,numberEnd)));
                }
                if ("-".equals(currentOp)){
                    result=result-(numberCC?computeCC(expression,numberStart,numberEnd-1):Integer.valueOf(expression.substring(numberStart,numberEnd)));
                }
                // 重置下一轮操作符
                currentOp=expression.substring(numberEnd,numberEnd+1);
                // 重置下一轮表达式的初始七点和终点以及默认不是复杂表达式
                numberEnd=numberEnd+1;
                numberStart=numberEnd;
                numberCC=false;
            }else {
                numberEnd++;
            }
        }
        if (result==null){
            result=numberCC?computeCC(expression,numberStart,numberEnd-1):Integer.valueOf(expression.substring(numberStart,numberEnd));
        }else {
            if ("+".equals(currentOp)){
                result=result+(numberCC?computeCC(expression,numberStart,numberEnd-1):Integer.valueOf(expression.substring(numberStart,numberEnd)));
            }
            if ("-".equals(currentOp)){
                result=result-(numberCC?computeCC(expression,numberStart,numberEnd-1):Integer.valueOf(expression.substring(numberStart,numberEnd)));
            }
        }
        return result;
    }

    // 乘除法表达式计算
    public static Integer computeCC(String expression,int start,int end){
        Integer result=null;
        String currentOp=null;
        Integer numberStart=start;
        Integer numberEnd=start;
        while (numberEnd<=end){
            if (expression.charAt(numberEnd)=='*'||expression.charAt(numberEnd)=='/'){
                if (result==null){
                    result=Integer.valueOf(expression.substring(numberStart,numberEnd));
                }
                if ("*".equals(currentOp)){
                    result=result*Integer.valueOf(expression.substring(numberStart,numberEnd));
                }
                if ("/".equals(currentOp)){
                    result=result/Integer.valueOf(expression.substring(numberStart,numberEnd));
                }
                currentOp=expression.substring(numberEnd,numberEnd+1);
                numberEnd=numberEnd+1;
                numberStart=numberEnd;
            }else {
                numberEnd=numberEnd+1;
            }
        }
        if (result==null){
            result=Integer.valueOf(expression.substring(numberStart,numberEnd));
        }else {
            if ("*".equals(currentOp)){
                result=result*Integer.valueOf(expression.substring(numberStart,numberEnd));
            }
            if ("/".equals(currentOp)){
                result=result/Integer.valueOf(expression.substring(numberStart,numberEnd));
            }
        }
        return result;
    }
}
