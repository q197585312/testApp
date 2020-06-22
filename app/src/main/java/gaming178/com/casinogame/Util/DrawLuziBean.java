package gaming178.com.casinogame.Util;


public class DrawLuziBean  {/*
	private int[] imageArray = { 0,R.drawable.bjl_luzi_1, R.drawable.bjl_luzi_2, R.drawable.bjl_luzi_3,
            R.drawable.bjl_luzi_4, R.drawable.bjl_luzi_5, R.drawable.bjl_luzi_6 ,R.drawable.bjl_luzi_7,R.drawable.bjl_luzi_8};
	Toast toast= null;
    public  void setBankerPlayerCount(String luzi,BaccaratTableInfo baccaratTableInfo)
    {
    	if ("null".equals(luzi) || luzi.length() <= 0 || "null#".equals(luzi)) {
            
            return;
        }
     
        int iBanker=0;
        int iPlayer=0;
        int iTie=0;
        int iBankerPair=0;
        int iPlayerPair=0;
        int iBig=0;
        int iSmall=0;
        String []array = luzi.split("\\#");
        if (array.length == 0) {
            return;
        }
        //NSLog(@"baccaratTableinfo_array:%@",array);
        for(int iNum=0;iNum<array.length;iNum++)
        {
            String sValue = array[iNum];
            if (sValue == null || sValue.length() <= 1) {
                break;
            }
           
            String []arrayDetail = sValue.split("\\-");
            String res = arrayDetail[0];
            

            String resBigSmall = arrayDetail[1];
                    
            if("1".equals(res)||
               "2".equals(res)||
               "3".equals(res)||
               "4".equals(res))
            {
                iBanker++;
                if ("2".equals(res)) {
                    iBankerPair ++;
                }else if("3".equals(res)){
                    iPlayerPair ++;
                }else if("4".equals(res)){
                    iPlayerPair ++;
                    iBankerPair ++;
                }
            }
            else if("5".equals(res)||
                    "6".equals(res)||
                    "7".equals(res)||
                    "8".equals(res))
            {
                iPlayer++;
                if ("6".equals(res)) {
                    iBankerPair ++;
                }else if("7".equals(res)){
                    iPlayerPair ++;
                }else if("8".equals(res)){
                    iPlayerPair ++;
                    iBankerPair ++;
                }
            }
            else if("9".equals(res)||
                    "10".equals(res)||
                    "11".equals(res)||
                    "12".equals(res))
            {
                iTie++;
                if ("10".equals(res)) {
                    iBankerPair ++;
                }else if("11".equals(res)){
                    iPlayerPair ++;
                }else if("12".equals(res)){
                    iPlayerPair ++;
                    iBankerPair ++;
                }
            }
            if ("5".equals(resBigSmall)) {
                iBig ++;
            }else {
                iSmall ++;
            }
        }
        baccaratTableInfo.setiBanker(iBanker);
        baccaratTableInfo.setiPlayer(iPlayer);
        baccaratTableInfo.setiTie(iTie);
        baccaratTableInfo.setiBankerPair(iBankerPair);
        baccaratTableInfo.setiPlayerPair(iPlayerPair);
        baccaratTableInfo.setiBig(iBig);
        baccaratTableInfo.setiSmall(iSmall);
        baccaratTableInfo.setiAll(iBanker+iPlayer+iTie);
    }
	@Override
	public String show_Bjl_Luzi(String luzi, FrameLayout framelayout,Context ctx, int x,
			int y,int iColCount,int xSpace,int ySpace,int picWidth,int picHeight) {
		if (luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
	        return "";
	    }
		String luziInfo[] = luzi.split("\\#");
	    if (luziInfo.length <= 0 && luziInfo.length >80) {//数据格式不对
	        return "" ;
	    }
	 //   Log.i("855play"," luzi length "+luziInfo.length);
	    int tieIndex = 0;
	    int bankerIndex = 0;
	    int playerIndex = 0;
	    int index = luziInfo.length ;
	    
	    String flag[] = new String [350];
	    for (int i = 0; i < 300; i++) {
	        flag[i] = "0";
	    }
	    //定义一个整型的二维数组60X60
	    int [][] arrayRoad = new int [30][30];
	    int col = 0;//列数
	    int row = 0;//行数
	    
	    boolean bJumpBanker = false;
	    int jumpBankerIndex = 0;
	    
	    boolean bJumpPlayer = false;
	    int jumpPlayerIndex = 0;
	    for (int i=0; i<index; i++)
	    {
	    	String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
	        int res = Integer.parseInt(luziInfoDetail[0]);
	   //     Log.i("855play"," luzi data "+i + " -- " +res);
	        if (res == 9 || res == 10 || res == 11 || res == 12) {//和不显示
	            if (tieIndex > 0) {//要移掉显示和的数字
	            	//移除最后一层的数字跟斜杠
	            	removeLastView(framelayout);
	                
	            }
	            tieIndex++;
	            ///////////显示和的路子
	            if (bankerIndex == 0 && playerIndex == 0) {//前面一个显示都还没有的特殊情况
	                
	            }else {//在最后一层上面添加和的信息
	            	show_Tie_Luzi(framelayout,tieIndex, ctx, picWidth, picHeight);
	            }
	        }
	        else if (res == 1 || res == 2 || res == 3 || res == 4) {//庄，要先判断前面一个是什么才知道怎么显示在哪一列
	            if (bankerIndex == 0 )
				{//和前面一个不一样
	                if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
	                    /////////下面又分前面一个是不是和的情况
	                    
	                    ///////////还要判断这个格子是否被填充了
	                    int insertIndex = col * iColCount + row;
	                    arrayRoad[row][col] = 1;
	                    
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                   
	                    	flag[insertIndex] = "1";
	                      //  [self show_Pic_Luzi:res :sv :x :y :0 :col :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
	                    }
	                    
	                    
	                }
	                else {//换列
	                    
	                    col++;
	                    row = 0;
	                    /////////////判断是否有插入了
	                    int insertIndex = (col+jumpBankerIndex) * iColCount + row;
	                    arrayRoad[row][col+jumpBankerIndex] = 1;
	                    if ("0".equals(flag[insertIndex])) {//允许插入，此种情况是顶部第一行被插入了
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                      //  [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpBankerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                      
	                    }
	                    ////////如果被插入了，转弯
	                    else {
	                        bJumpBanker = true;
	                        jumpBankerIndex ++;
	                        col++;
	                        insertIndex = col * iColCount + row;
	                        flag[insertIndex] = "1";
	                     //   [self show_Pic_Luzi:res :sv :x :y :0 :col :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }
	                    
	                    
	                }
	            }else {//继续庄，不换列,换行
	                
	                if (bJumpBanker == true) {
	                    
	                    row++;
	                    int insertIndex = (col+jumpBankerIndex) * iColCount + row;
	                    arrayRoad[row][col+jumpBankerIndex] = 1;
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                    //    [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpBankerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }else {
	                        jumpBankerIndex ++;
	                        insertIndex = (col+jumpBankerIndex) * iColCount + row;
	                        flag[insertIndex] = "1";
	                     //   [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpBankerIndex) :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                       
	                    }
	                    
	                }else {
	                    row++;
//	                    if (row > 6) {
//	                        row = 6;
//	                        ///////需要换列
//	                        jumpBankerIndex ++;
//	                    }
	                    if (row > iColCount-1) {
	                        row = iColCount-1;
	                        ///////需要换列
	                        jumpBankerIndex ++;
	                    }
	                    int insertIndex = (col+jumpBankerIndex) * iColCount + row;
	                    arrayRoad[row][col+jumpBankerIndex] = 1;
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                   //     [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpBankerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                       
	                    }else {
	                        jumpBankerIndex ++;
	                        row --;
	                        insertIndex = (col+jumpBankerIndex) * iColCount + row;
	                        flag[insertIndex] = "1";
	                     //   [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpBankerIndex) :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                       
	                    }
	                    
	                }
	                
	                
	            }
	            bankerIndex = res;
	            playerIndex = 0;
	            tieIndex = 0;
	            jumpPlayerIndex = 0;
	            bJumpPlayer = false;
	        }
	        else if(res == 5 || res == 6 || res == 7 || res == 8){//闲,，要先判断前面一个是什么才知道怎么显示在哪一列
	            if (playerIndex == 0 ) {//和前面一个不一样
	                if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
	                    /////////下面又分前面一个是不是和的情况
	                    
	                    ///////////还要判断这个格子是否被填充了
	                    int insertIndex = col * iColCount + row;
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                    //    [self show_Pic_Luzi:res :sv :x :y :0 :col :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
	                      
	                    }
	                    arrayRoad[row][col] = 2;
	                    
	                }
	                else {//换列
	                    
	                    col++;
	                    row = 0;
	                    /////////////判断是否有插入了
	                    int insertIndex = (col+jumpPlayerIndex) * iColCount + row;
	                    arrayRoad[row][col] = 2;
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                  //      [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }
	                    ////////如果被插入了，转弯
	                    else {
	                        bJumpPlayer = true;
	                        jumpPlayerIndex ++;
	                        col++;
	                        insertIndex = col * iColCount + row;
	                        flag[insertIndex] = "1";
	                   //     [self show_Pic_Luzi:res :sv :x :y :0 :col :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
	                       
	                    }
	                    
	                    
	                }
	            }else {//继续闲，不换列,换行
	                
	                if (bJumpPlayer == true) {
	                    row ++;
	                    int insertIndex = (col+jumpPlayerIndex) * iColCount + row;
	                    arrayRoad[row][col+jumpPlayerIndex] = 2;
	                    if ("0".equals(flag[insertIndex])) {
	                    	flag[insertIndex] = "1";
	                      //  [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }else {
	                        jumpPlayerIndex ++;
	                        insertIndex = (col+jumpPlayerIndex) * iColCount + row;
	                        flag[insertIndex] = "1";
	                     //   [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                       
	                    }
	                    
	                }else {
	                    row++;
//	                    if (row > 6) {
//	                        row = 6;
//	                        ///////需要换列
//	                        jumpPlayerIndex ++ ;
//	                    }
	                    if (row > iColCount-1) {
	                        row = iColCount-1;
	                        ///////需要换列
	                        jumpPlayerIndex ++ ;
	                    }
	                    int insertIndex = (col+jumpPlayerIndex) * iColCount + row;
	                    arrayRoad[row][col+jumpPlayerIndex] = 2;
	                    if ("0".equals(flag[insertIndex])) {//允许插入
	                        //插入图片
	                    	flag[insertIndex] = "1";
	                   //     [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
	                    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }else {
	                        jumpPlayerIndex ++;
	                        row --;//转弯的话，行数不加
	                        insertIndex = (col+jumpPlayerIndex) * iColCount + row;
	                        flag[insertIndex] = "1";
	                      //  [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
	                        show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
	                        
	                    }
	                    
	                }
	                
	                
	            }
	            playerIndex = res;
	            bankerIndex = 0;
	            tieIndex = 0;
	            jumpBankerIndex = 0;
	            bJumpBanker = false;
	        }
	    }
	    ctx = null;
	
	    int iStart = 0;
	    String smallLuzi ="";
	    

	    //大眼路
	  //  smallLuzi = GetSmallRoad(arrayRoad,1,2,2,1);
	  //小眼路
	 //   smallLuzi = GetSmallRoad(arrayRoad,2,3,3,2);
	    //小强路
	    smallLuzi = GetSmallRoad(arrayRoad,3,4,4,3);
	    Log.i("LanjianTest", "++++++"+luzi);
	    Log.i("LanjianTest", "------"+smallLuzi);
	    return smallLuzi;

	}
	*
	 * 
	 * @param road 大路
	 * @param startCol1 开始位置，大眼路从第二列开始startCol1是1，startCol2是2，小眼路(2,3),小强路(3,4)
	 * @param startCol2 前面的startCol1上无路子，从startCol2开始
	 * @param compareCol1 比对齐整的列 ,大眼路2，小眼路3，小强路4
	 * @param compareCol2 比对有无的列，大眼路1，小眼路2，小强路3
	 * @return

	public String GetSmallRoad(int [][] road,int startCol1,int startCol2,int compareCol1,int compareCol2)
	{
		String smallLuzi = "";
		int row = 0;
		int col = 0;
		int iStart = 0;
		if(road[1][startCol1] > 0){//从第二行第二列开始判断
			switch(startCol1)
			{
			case 1:
				iStart = 31;
				break;
			case 2:
				iStart = 61;
				break;
			case 3:
				iStart = 91;
				break;
			default:
				iStart = 0;
					break;
			}
	    	
	    	
	    	
	    }else if(road[0][startCol2] > 0){
	    	
	    	switch(startCol2)
			{
			case 2:
				iStart = 60;
				break;
			case 3:
				iStart = 90;
				break;
			case 4:
				iStart = 120;
				break;
			default:
				iStart = 0;
					break;
			}
	    }else{
	    	iStart = 0;
	    }
	    if(iStart > 0){
	    	row = 0;
	    	col = 0;
	    	for(int i=iStart;i<900;i++)// 从第31个数开始，要计算出来第31属于几行几列，求余数计算出行,整除计算出列数
	    	{
	    		 row = i%30;
	    		 col = i/30;
	    		 //最先要判断第0行是否有数据，如果没有数据后后面的就不需要再循环了，循环结束,如果不是第0行，只需要跳出本次循环即可
	    		 if(road[0][col] == 0)
	    			 break;
	    		 else if(road[row][col] == 0){
	    			 continue;
	    		 }
	    		 //先判断是否是第0行，如果是就需要判断齐整，如果不是先判断有无，再判断是否是直落
	    		 if(row == 0)//齐整
	    		 {
	    			 //判断前一列，跟前前一列路子个数是否相等
	    			  if((col-1) >= 0 && (col-compareCol1) >= 0 ){
	    				  int iCount1=0;//前一列路子个数
	    				  int iCount2=0;//前前一列路子个数
	    				  for(int j=0;j<30;j++)
	    				  {
	    					  if(road[j][col-1] > 0)
	    						  iCount1++;
	    					  else
	    						  break;
	    				  }
	    				  for(int j=0;j<30;j++)
	    				  {
	    					  if(road[j][col-compareCol1] > 0)
	    						  iCount2++;
	    					  else
	    						  break;
	    				  }
	    				  if(iCount1==iCount2){//齐整
	    					  smallLuzi +="1#";
	    				  }else{
	    					  smallLuzi +="5#";
	    				  }
	    				  
	    			  }
	    			  
	    		 }
	    		 else//不需要判断齐整
	    		 {
	    			 //先判断有有无
	    			 if(road[row][col-compareCol2] > 0)//有
	    				 smallLuzi +="1#";
	    			 else//如果是无，则再需要判断直落
	    			 {
	    				  if(road[row-1][col-compareCol2] == 0)//此参照点的上一个前一 个路子也是无，那么是直落
	    				  {
	    					  smallLuzi +="1#";
	    				  }else
	    				  {
	    					  smallLuzi +="5#";
	    				  }
	    			 }
	    		 }
	    	}
	    }
		return smallLuzi;
	}
	public void show_Pic_Luzi(int res,FrameLayout framelayout,Context ctx,int x,int y,int tieIndex,
			int col,int row,int xSpace,int ySpace,int picWidth,int picHeight)
	{
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(picWidth, picHeight);
		
		ImageView imageView = new ImageView(ctx);
		params.leftMargin = x+(col*xSpace);//距离右边路子的宽度
		params.topMargin = y+(row*ySpace);//距离下面路子的高度
		
		imageView.setBackgroundResource(imageArray[res]);
		imageView.setLayoutParams(params);
		framelayout.addView(imageView);
		if(tieIndex > 0)
		{
			//先加斜线
			FrameLayout.LayoutParams paramsXiexian = new FrameLayout.LayoutParams(picWidth, picHeight);
			ImageView imageViewXiexian = new ImageView(ctx);
			paramsXiexian.leftMargin = params.leftMargin + 5;
			paramsXiexian.topMargin = params.topMargin + 3;
			
			imageViewXiexian.setBackgroundResource(R.drawable.bjl_luzi_xiexian);
			imageViewXiexian.setLayoutParams(paramsXiexian);
			framelayout.addView(imageViewXiexian);
			///添加数字
			FrameLayout.LayoutParams paramsText = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
					FrameLayout.LayoutParams.WRAP_CONTENT);
			TextView textView = new TextView(ctx);
			paramsText.leftMargin = params.leftMargin + 5;
			paramsText.topMargin = params.topMargin + 1;
			textView.setLayoutParams(paramsText);
			final String tieText = "" + tieIndex;
			textView.setText(tieText);
			textView.setTextSize(9);
			textView.setTextColor(0xffffffff);
			framelayout.addView(textView);
			
			textView = null;
			paramsText = null;
			imageViewXiexian = null;
			paramsXiexian = null;
		}
		imageView = null;
		params = null;
		ctx = null;
	}
	public void removeLuziById(FrameLayout frameLayout)//删除所有的路子，但是不删除显示路子的背景图片
	{
	//	FrameLayout frameLayout = (FrameLayout) android.app.Activity.findViewById(framelayoutLuzi);
		int iCount = frameLayout.getChildCount();
	//	System.out.println(iCount + " -----------------");
		View imageView = null;
		for(int i=1;i<iCount;i++)
		{
		//	System.out.println(i + " -----------------");
			imageView = (View)frameLayout.getChildAt(1);//始终删除最上面那个
		//	if(imageView.getId() != R.id.m_imageview_luzi_b01){
		//	System.out.println(imageView.getId() + " -----------------");
			if(imageView!=null)
				frameLayout.removeViewAt(1);
		//	}
		}
		
	}
	public void removeLastView(FrameLayout framelayout)//移除数字跟斜杠
	{
		int iCount = framelayout.getChildCount();
		if(iCount>2){
			framelayout.removeViewAt(iCount-1);
			framelayout.removeViewAt(iCount-2);
		}
		
	}
	public void show_Tie_Luzi(FrameLayout framelayout,int tieIndex,Context ctx,int picWidth,int picHeight)
	{
		int iCount = framelayout.getChildCount();
		if(iCount<=0)
			return;
		View imageView = framelayout.getChildAt(iCount-1);
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)imageView.getLayoutParams();
		//先加斜线
		FrameLayout.LayoutParams paramsXiexian = new FrameLayout.LayoutParams(picWidth, picHeight);
		ImageView imageViewXiexian = new ImageView(ctx);
		paramsXiexian.leftMargin = params.leftMargin + 5;
		paramsXiexian.topMargin = params.topMargin + 3;
		
		imageViewXiexian.setBackgroundResource(R.drawable.bjl_luzi_xiexian);
		imageViewXiexian.setLayoutParams(paramsXiexian);
		framelayout.addView(imageViewXiexian);
		///添加数字
		FrameLayout.LayoutParams paramsText = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		TextView textView = new TextView(ctx);
		paramsText.leftMargin = params.leftMargin + 5;
		paramsText.topMargin = params.topMargin + 1;
		textView.setLayoutParams(paramsText);
		final String tieText = "" + tieIndex;
		textView.setText(tieText);
		textView.setTextSize(9);
		textView.setTextColor(0xffffffff);
		framelayout.addView(textView);
		
		textView = null;
		paramsText = null;
		imageViewXiexian = null;
		paramsXiexian = null;
		ctx = null;
	}
	public void addBjlLuziView(FrameLayout framelayout,Context ctx,int iMarginTop,int iMarginLeft,int picWidth,int picHeight)
	{
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(picWidth,picHeight);
		ImageView imageView = new ImageView(ctx);
		params.leftMargin = iMarginLeft;
		params.topMargin = iMarginTop;
		
		imageView.setBackgroundResource(R.drawable.bjl_luzi_1);
		imageView.setLayoutParams(params);
		framelayout.addView(imageView);
		
		imageView = null;
		params = null;
		ctx = null;
		
	}
	public void showShuffleView(FrameLayout framelayout,Context ctx,int iMarginTop,int iMarginLeft,String strText)
	{
	//	View imageView = framelayout.getChildAt(0);
	//	if(imageView == null)
	//		return;
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)framelayout.getLayoutParams();
		
		FrameLayout.LayoutParams paramsShuffle = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		TextView textViewShuffle = new TextView(ctx);
		paramsShuffle.leftMargin = params.leftMargin + iMarginLeft;
		paramsShuffle.topMargin = params.topMargin + iMarginTop;
		
		textViewShuffle.setText(strText);
		textViewShuffle.setTextColor(0xffffffff);
		textViewShuffle.setTypeface(Typeface.SERIF);
		TextPaint paint = textViewShuffle.getPaint();
		paint.setFakeBoldText(true); 
		textViewShuffle.setLayoutParams(paramsShuffle);
		framelayout.addView(textViewShuffle);
		
		paramsShuffle = null;
		textViewShuffle = null;
		ctx = null;
	}
	public void showChipView(FrameLayout framelayout,Context ctx,int x,int y,int with,int high,int chipIndex,int pos)
	{
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(with,high);
		ImageView imageView = new ImageView(ctx);
		params.leftMargin = x;
		params.topMargin = y-(pos*5);
		switch(chipIndex)
		{
		case 1000000:
			imageView.setBackgroundResource(R.drawable.btn_chip_1000000);
		break;
		case 500000:
			imageView.setBackgroundResource(R.drawable.btn_chip_500000);
		break;
		case 100000:
			imageView.setBackgroundResource(R.drawable.btn_chip_100000);
		break;
		case 50000:
			imageView.setBackgroundResource(R.drawable.btn_chip_50000);
		break;
		case 10000:
			imageView.setBackgroundResource(R.drawable.btn_chip_10000);
		break;
		case 5000:
			imageView.setBackgroundResource(R.drawable.btn_chip_5000);
		break;
		case 1000:
			imageView.setBackgroundResource(R.drawable.btn_chip_1000);
		break;
		case 500:
			imageView.setBackgroundResource(R.drawable.btn_chip_500);
		break;
		case 100:
			imageView.setBackgroundResource(R.drawable.btn_chip_100);
		break;
		case 50:
			imageView.setBackgroundResource(R.drawable.btn_chip_50);
		break;
		case 10:
			imageView.setBackgroundResource(R.drawable.btn_chip_10);
		break;
		case 5:
			imageView.setBackgroundResource(R.drawable.btn_chip_5);
		break;
		case 1:
			imageView.setBackgroundResource(R.drawable.btn_chip_1);
		break;
		}
		
		imageView.setLayoutParams(params);
		framelayout.addView(imageView);
		imageView = null;
		ctx = null;
	}
	public int showChip(int moneyChip,FrameLayout framelayout,Context ctx,int x,int y,int with,int high,int tipsX,int tipsY,int tipsWith
			,int tipsHigh)
	{
		removeLuziById(framelayout);
		int iCount = 0;
	    int iPosCount = 0;
	    int moneyTips = moneyChip;  //传入金额
	    
	    int iChip = moneyChip / 1000000;
	    while (iCount < iChip) {
	        
	     //   [self showChipPic:x :y :iPosCount :region_chip:1000000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,1000000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    int betMoney = moneyChip % 1000000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 500000;
	    while (iCount < iChip) {
	        
	     //   [self showChipPic:x :y :iPosCount :region_chip:500000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,500000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 500000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 100000;
	    while (iCount < iChip) {
	        
	    //    [self showChipPic:x :y :iPosCount :region_chip:100000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,100000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 100000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 50000;
	    while (iCount < iChip) {
	        
	    //    [self showChipPic:x :y :iPosCount :region_chip:50000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,50000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 50000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 10000;
	    while (iCount < iChip) {
	        
	  //      [self showChipPic:x :y :iPosCount :region_chip:10000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,10000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 10000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 5000;
	    while (iCount < iChip) {
	        
	   //     [self showChipPic:x :y :iPosCount :region_chip:5000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,5000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 5000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 1000;
	    while (iCount < iChip) {
	        
	    //    [self showChipPic:x :y :iPosCount :region_chip:1000:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,1000,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 1000;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 500;
	    while (iCount < iChip) {
	        
	    //    [self showChipPic:x :y :iPosCount :region_chip:500:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,500,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 500;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 100;
	    while (iCount < iChip) {
	        
	   //     [self showChipPic:x :y :iPosCount :region_chip:100:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,100,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 100;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 50;
	    while (iCount < iChip) {
	        
	     //   [self showChipPic:x :y :iPosCount :region_chip:50:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,50,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 50;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 10;
	    while (iCount < iChip) {
	        
	   //     [self showChipPic:x :y :iPosCount :region_chip:10:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,10,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 10;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 5;
	    while (iCount < iChip) {
	        
	     //   [self showChipPic:x :y :iPosCount :region_chip:5:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,5,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    betMoney = moneyChip % 5;
	    if (betMoney == 0) {
	    	showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	        return moneyTips;
	    }
	    iCount = 0;
	    moneyChip = betMoney;
	    iChip = moneyChip / 1;
	    while (iCount < iChip) {
	        
	   //     [self showChipPic:x :y :iPosCount :region_chip:1:width:high];
	    	showChipView(framelayout,ctx,x,y,with,high,1,iPosCount);
	        iCount ++;
	        iPosCount ++;
	    }
	    showChipMoneyTips(framelayout,ctx,tipsX, tipsY, tipsWith, tipsHigh,moneyTips,iPosCount);
	    ctx = null;
	    return moneyTips;
		
	}
	public void showChipMoneyTips(FrameLayout framelayout,Context ctx,int x,int y,int with,int high,int money,int pos)
	{
		if(money <= 0)
			return ;
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(with,high);
		ImageView imageView = new ImageView(ctx);
		params.leftMargin = x;
		params.topMargin = y;
		imageView.setBackgroundResource(R.drawable.tips);
		imageView.setLayoutParams(params);
		framelayout.addView(imageView);
		
		FrameLayout.LayoutParams paramsText = new FrameLayout.LayoutParams(with-1,high-2);
		TextView textView = new TextView(ctx);
		paramsText.leftMargin = x + 2;
		paramsText.topMargin = y - 2;
		
		
		textView.setTextColor(0xffffffff);
		textView.setTextSize(10);
		textView.setTypeface(Typeface.SERIF);
		textView.setGravity(Gravity.CENTER);
	//	textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		TextPaint paint = textView.getPaint();
		paint.setFakeBoldText(true); 
		textView.setText(""+money);
		textView.setLayoutParams(paramsText);
		framelayout.addView(textView);
		
		textView = null;
		paramsText = null;
		imageView = null;
		params = null;
		ctx = null;
	}
	public int getScrollDistance(int chip)
	{
		int value = 0;
	    if (chip > 1 && chip <= 5) {
	        value = 1;
	    }else if(chip > 5 && chip <= 10)
	    {
	        value = 2;
	    }
	    else if(chip > 10 && chip <= 50)
	    {
	        value = 3;
	    }else if(chip > 50 && chip <= 100)
	    {
	        value = 4;
	    }else if(chip > 100 && chip <= 500)
	    {
	        value = 5;
	    }else if(chip > 500 && chip <= 1000)
	    {
	        value = 6;
	    }else if(chip > 1000 && chip <= 5000)
	    {
	        value = 7;
	    }else if(chip > 5000 )
	    {
	        value = 7;
	    }
	    return value;
	}
	public void showToast(String strMessage,Context ctx,int timeElapse)
    {
    	if (toast == null) {
    		toast=Toast.makeText(ctx, strMessage, timeElapse);
    		toast.setGravity(Gravity.CENTER, 0, 90);
        } else {
            toast.setText(strMessage);
        }
        toast.show();
        ctx = null;
    }
	public void startBackgroudMuzicService(int index,ComponentName component,Context ctx,int volume)
    {
    	Intent mIntent = null;
    	String strMuzic = "";
    	switch(index)
    	{
    	case 1:
    		strMuzic = BackgroudMuzicService.PLAY_SONG1;
    		break;
    	case 2:
    		strMuzic = BackgroudMuzicService.PLAY_SONG2;
    		break;
    	case 3:
    		strMuzic = BackgroudMuzicService.PLAY_SONG3;
    		break;
    	}
    	mIntent = new Intent(strMuzic);  
    	mIntent.putExtra("volume", volume);
    	mIntent.setComponent(component);  
    	ctx.startService(mIntent);
    	mIntent = null;
    	ctx = null;
    }
	public void changeMuzicVolumeService(ComponentName component,Context ctx,int volume,String action)
    {
    	Intent mIntent = null;
    	
    	mIntent = new Intent(action);  
    	mIntent.putExtra("volume", volume);
    	mIntent.setComponent(component);  
    	ctx.startService(mIntent);  
    	mIntent = null;
    	ctx = null;
    }
	public void closeMuzicService(Context ctx, Class<?> cls)
	{
		Intent intent = new Intent(ctx,cls); 
	      //停止服务  
		ctx.stopService(intent); 
		intent = null;
		ctx = null;
	//	Log.i(WebSiteUrl.Tag, "closeMuzicService="+ctx);
	}
	public void startFrontMuzicService(String action,int index,ComponentName component,Context ctx,int volume)
    {
    	Intent mIntent = null;
   
    //	Log.i(WebSiteUrl.Tag, "startFrontMuzicService="+action);
    	mIntent = new Intent(action);  
    	mIntent.putExtra("muzic_index", index);
    	mIntent.putExtra("volume", volume);
    	mIntent.setComponent(component);  
    	ctx.startService(mIntent);  
    	mIntent = null;
    	ctx = null;
    }
	public  Bitmap readBitMap(Context context, int resId){
    	BitmapFactory.Options opt = new BitmapFactory.Options();
    //	opt.inPreferredConfig = Bitmap.Config.RGB_565; 
    	opt.inPreferredConfig = Bitmap.Config.ARGB_8888; 
    	opt.inPurgeable = true;
    	opt.inInputShareable = true;

    	//获取资源图片
    	InputStream is = context.getResources().openRawResource(resId);
    	context = null;
    	return BitmapFactory.decodeStream(is,null,opt);
    }*/
}
