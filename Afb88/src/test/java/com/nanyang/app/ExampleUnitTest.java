package com.nanyang.app;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String id= "|1|50|452265||";
        String[] split = id.split("\\|");
        if (split.length > 3) {
            String a = split[2];;
        }
       /* String s = "伯恩利 (X)\\n @ 3.10\\n||r=1968463123|5|13";
        String[] split = s.split("\\|");
        System.out.println(split.length);
        if (split.length == 5) {
            System.out.println(split.length);
        }*/
    }


}

       /* #pragma mark - --------全场半场 让球和大小 4
        NSArray * FToddsArr=[dicAllData objectForKey:@"FTodds"];
        NSArray * FHoddsArr=[dicAllData objectForKey:@"FHodds"];
//    NSLog(@"全场 让球 大小~~~%@",FToddsArr);
//    NSLog(@"半场 让球 大小~~~%@",FHoddsArr);

        if (FToddsArr.count>0||FHoddsArr.count>0) {

        //title
        moreBet4ItemsSubView * moreBet4ItemsSubviewtitle=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:0];
        [moreBet4ItemsSubviewtitle setFrame:CGRectMake(0, 0, SCREEN_WITDTH, rowFor4RowH)];
        [moreBet4ItemsSubviewtitle titleForItems:@[[NSString stringWithFormat:@"%@%@",lang.mFT,lang.mHDP],
        [NSString stringWithFormat:@"%@%@",lang.mFT,lang.mOU],
        [NSString stringWithFormat:@"%@%@",lang.mFH,lang.mHDP],
        [NSString stringWithFormat:@"%@%@",lang.mFH,lang.mOU]]];
        [self.viewForDraw addSubview:moreBet4ItemsSubviewtitle];

        H1All=H1All+rowFor4RowH;

        //数据
        NSInteger countFT=FToddsArr.count;
        NSInteger countFH=FHoddsArr.count;
        NSInteger total=countFT>=countFH?countFT:countFH;
        for (int i=0; i<total; i++) {
        //全场半场 主场 大
        moreBet4ItemsSubView * moreBet4ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:0];
        [moreBet4ItemsSubview setFrame:CGRectMake(0, H1All-7+(rowFor4RowH+5)*2*i, SCREEN_WITDTH, rowFor4RowH)];
        [moreBet4ItemsSubview oddsForRow1F:[Util checkArrBeyondIndex:FToddsArr andIndex:i] andH:[Util checkArrBeyondIndex:FHoddsArr andIndex:i]];
        [self.viewForDraw addSubview:moreBet4ItemsSubview];
        WSelf;
        moreBet4ItemsSubview.btnclick = ^(NSInteger index,NSDictionary *dicFTodds,NSDictionary *dicFHodds) {
        SSelf;
        //0全场.主场 1全场.大 2半场.主场 3半场.大
        NSString * strType=nil;
        BOOL ishaf=NO;
        FootBallItem * item=[[FootBallItem alloc]init];
        NSDictionary * dicSelect=nil;
        switch (index) {
        case 0:
        strType=@"home";
        dicSelect=dicFTodds;
        break;
        case 1:
        strType=@"over";
        dicSelect=dicFTodds;
        break;
        case 2:
        strType=@"home";
        ishaf=YES;
        dicSelect=dicFHodds;
        break;
        case 3:
        strType=@"over";
        ishaf=YES;
        dicSelect=dicFHodds;
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[dicSelect[@"SocOddsId"] integerValue]];
        item.mHasPar=[NSNumber numberWithBool:[dicSelect[@"HasPar"] boolValue]];
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|%ld|%@||",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|%ld|%@||",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        if (ishaf) {
        strBETID=[NSString stringWithFormat:@"s|%@|%ld||%@|",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        strBETID_Par=[NSString stringWithFormat:@"s|%@_par|%ld||%@|",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        }
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        //全场半场 客场 小

        冯大川, [20.04.19 14:39]
        moreBet4ItemsSubView * moreBet4ItemsSubview2=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:0];
        [moreBet4ItemsSubview2 setFrame:CGRectMake(0, H1All-7+(rowFor4RowH+5)*2*i+rowFor4RowH+5, SCREEN_WITDTH, rowFor4RowH)];
        [moreBet4ItemsSubview2 oddsForRow2F:[Util checkArrBeyondIndex:FToddsArr andIndex:i] andH:[Util checkArrBeyondIndex:FHoddsArr andIndex:i]];
        [self.viewForDraw addSubview:moreBet4ItemsSubview2];
        moreBet4ItemsSubview2.btnclick = ^(NSInteger index,NSDictionary *dicFTodds,NSDictionary *dicFHodds) {
        SSelf;
        //0全场.客场 1全场.小 2半场.客场 3半场.小
        NSString * strType=nil;
        BOOL ishaf=NO;
        FootBallItem * item=[[FootBallItem alloc]init];
        NSDictionary * dicSelect=nil;
        switch (index) {
        case 0:
        strType=@"away";
        dicSelect=dicFTodds;
        break;
        case 1:
        strType=@"under";
        dicSelect=dicFTodds;
        break;
        case 2:
        strType=@"away";
        ishaf=YES;
        dicSelect=dicFHodds;
        break;
        case 3:
        strType=@"under";
        ishaf=YES;
        dicSelect=dicFHodds;
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[dicSelect[@"SocOddsId"] integerValue]];
        item.mHasPar=[NSNumber numberWithBool:[dicSelect[@"HasPar"] boolValue]];
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|%ld|%@||",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|%ld|%@||",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        if (ishaf) {
        strBETID=[NSString stringWithFormat:@"s|%@|%ld||%@|",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        strBETID_Par=[NSString stringWithFormat:@"s|%@_par|%ld||%@|",strType,[sSelf->_myTypeG integerValue],item.mSocOddsId];
        }
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }

        };
        }

        H1All=H1All-7+(rowFor4RowH+5)*2*(total-1)+rowFor4RowH+5+rowFor4RowH;
        }

        #pragma mark - --------全场半场 主和客 3 F1X2_SocOddsId H1X2_SocOddsId
        NSArray *QuanZhuHeKeKey=@[@"1",lang.mXX,@"2"];
        NSArray *QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"F1"]?[dicAllData objectForKey:@"F1"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FX"]?[dicAllData objectForKey:@"FX"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"F2"]?[dicAllData objectForKey:@"F2"]:@""]];
//    NSLog(@"全场 主和客~~~%@",QuanZhuHeKeValue);

        if ([[dicAllData objectForKey:@"F1"] floatValue]!=0||[[dicAllData objectForKey:@"FX"] floatValue]!=0||[[dicAllData objectForKey:@"F2"] floatValue]!=0) {
        //全场.主和客
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=[NSString stringWithFormat:@"%@%@",lang.mFT,lang.mX12];
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];

        冯大川, [20.04.19 14:39]
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        moreBet3ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labDown1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp2.textColor=RGBCOLOR(21, 46, 255);
        moreBet3ItemsSubview.labDown2.textColor=RGBCOLOR(21, 46, 255);
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"1";
        break;
        case 1:
        strType=@"X";
        break;
        case 2:
        strType=@"2";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"F1X2_SocOddsId"] integerValue]];
        item.mHasPar=sSelf.myItem.mHasPar;
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|5|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|5|%@||",strType,item.mSocOddsId];
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;
        }

        NSArray *BanZhuHeKeKey=@[@"1",lang.mXX,@"2"];
        NSArray *BanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"H1"]?[dicAllData objectForKey:@"H1"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HX"]?[dicAllData objectForKey:@"HX"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"H2"]?[dicAllData objectForKey:@"H2"]:@""]];
//    NSLog(@"全场 主和客~~~%@",BanZhuHeKeValue);

        if ([[dicAllData objectForKey:@"H1"] floatValue]!=0||[[dicAllData objectForKey:@"HX"] floatValue]!=0||[[dicAllData objectForKey:@"H2"] floatValue]!=0) {
        //半场.主和客
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=[NSString stringWithFormat:@"%@%@",lang.mFH,lang.mX12];
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:BanZhuHeKeKey andValues:BanZhuHeKeValue];
        moreBet3ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labDown1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp2.textColor=RGBCOLOR(21, 46, 255);
        moreBet3ItemsSubview.labDown2.textColor=RGBCOLOR(21, 46, 255);
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"1";
        break;
        case 1:
        strType=@"X";
        break;
        case 2:
        strType=@"2";
        break;
default:
        break;
        }

        冯大川, [20.04.19 14:39]
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"H1X2_SocOddsId"] integerValue]];
        item.mHasPar=sSelf.myItem.mHasPar;
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|5|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|5|%@||",strType,item.mSocOddsId];
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;
        }

        #pragma mark - --------全场波胆 3 FCS_SocOddsId
//    NSLog(@"全场波胆~~~%@",[self BackQuanCBoDan:dicAllData]);
        NSMutableDictionary * dicForQuanCBoDan=[[DataManager manager] BackQuanCBoDan:dicAllData];
        NSMutableArray * arrCol1=[[DataManager manager] getArrForCol1:dicForQuanCBoDan];
        NSMutableArray * arrCol2=[[DataManager manager] getArrForCol2:dicForQuanCBoDan];
        NSMutableArray * arrCol3=[[DataManager manager] getArrForCol3:dicForQuanCBoDan];
        if (arrCol1.count>0||arrCol2.count>0||arrCol3.count>0) {
        //全场波膽
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFTCorrectScore;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        NSInteger total=arrCol1.count>=arrCol2.count?arrCol1.count:arrCol2.count;
        total=total>=arrCol3.count?total:arrCol3.count;
        for (int i=0; i<total; i++) {
        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7+(rowFor3RowH+5)*i, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrCol1:[Util checkArrBeyondIndex:arrCol1 andIndex:i] andCol2:[Util checkArrBeyondIndex:arrCol2 andIndex:i] andCol3:[Util checkArrBeyondIndex:arrCol3 andIndex:i]];
        moreBet3ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp2.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp3.textColor=[UIColor redColor];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];

        moreBet3ItemsSubview.isBoDanHF=NO;
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=[NSString stringWithFormat:@"%ld",index];
        FootBallItem * item=[[FootBallItem alloc]init];

        冯大川, [20.04.19 14:39]
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"FCS_SocOddsId"] integerValue]];
        item.mHasPar=@(0);//没有 过关
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|csr|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";//没有 过关
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        }

        H1All=H1All-7+(rowFor3RowH+5)*(total-1)+rowFor3RowH;
        }


        #pragma mark - --------半场波胆 3 HCS_SocOddsId
//    NSLog(@"半场波胆~~~%@",[self BackBanCBoDan:dicAllData]);
        dicForQuanCBoDan=[[DataManager manager] BackBanCBoDan:dicAllData];
        arrCol1=[[DataManager manager] getArrForCol1:dicForQuanCBoDan];
        arrCol2=[[DataManager manager] getArrForCol2:dicForQuanCBoDan];
        arrCol3=[[DataManager manager] getArrForCol3:dicForQuanCBoDan];
        if (arrCol1.count>0||arrCol2.count>0||arrCol3.count>0) {
        //半场波膽
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFHCorrectScore;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        NSInteger total=arrCol1.count>=arrCol2.count?arrCol1.count:arrCol2.count;
        total=total>=arrCol3.count?total:arrCol3.count;
        for (int i=0; i<total; i++) {
        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7+(rowFor3RowH+5)*i, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrCol1:[Util checkArrBeyondIndex:arrCol1 andIndex:i] andCol2:[Util checkArrBeyondIndex:arrCol2 andIndex:i] andCol3:[Util checkArrBeyondIndex:arrCol3 andIndex:i]];
        moreBet3ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp2.textColor=[UIColor redColor];
        moreBet3ItemsSubview.labUp3.textColor=[UIColor redColor];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];

        moreBet3ItemsSubview.isBoDanHF=YES;
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=[NSString stringWithFormat:@"%ld",index];
        FootBallItem * item=[[FootBallItem alloc]init];

        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HCS_SocOddsId"] integerValue]];
        item.mHasPar=@(0);//没有 过关
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|csr|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";//没有 过关
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        }
        H1All=H1All-7+(rowFor3RowH+5)*(total-1)+rowFor3RowH;
        }

        #pragma mark - --------全场半场 双重机会 3  FDB_SocOddsId HDB_SocOddsId
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"FHA"]);//12
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"FHD"]);//1x
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"FDA"]);//x2
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"HHA"]);//12
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"HHD"]);//1x
//    NSLog(@"全场半场 双重机会~~~%@",[dicAllData objectForKey:@"HDA"]);//x2

        QuanZhuHeKeKey=@[@"1X",@"12",@"X2"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FHD"]?[dicAllData objectForKey:@"FHD"]:@""],

        冯大川, [20.04.19 14:39]
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FHA"]?[dicAllData objectForKey:@"FHA"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FDA"]?[dicAllData objectForKey:@"FDA"]:@""]];

        if ([[dicAllData objectForKey:@"FHD"] floatValue]!=0||[[dicAllData objectForKey:@"FHA"] floatValue]!=0||[[dicAllData objectForKey:@"FDA"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFTDoubleChance;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"10";
        break;
        case 1:
        strType=@"12";
        break;
        case 2:
        strType=@"2";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"FDB_SocOddsId"] integerValue]];
        item.mHasPar=@(0);//没有 过关
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|dc|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";//没有 过关
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;
        }

        BanZhuHeKeKey=@[@"1X",@"12",@"X2"];
        BanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HHD"]?[dicAllData objectForKey:@"HHD"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HHA"]?[dicAllData objectForKey:@"HHA"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HDA"]?[dicAllData objectForKey:@"HDA"]:@""]];


        if ([[dicAllData objectForKey:@"HHD"] floatValue]!=0||[[dicAllData objectForKey:@"HHA"] floatValue]!=0||[[dicAllData objectForKey:@"HDA"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFHDoubleChance;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:BanZhuHeKeKey andValues:BanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"10";
        break;
        case 1:
        strType=@"12";
        break;

        冯大川, [20.04.19 14:39]
        case 2:
        strType=@"2";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HDB_SocOddsId"] integerValue]];
        item.mHasPar=@(0);//没有 过关
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|dc|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";//没有 过关
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;
        }

        #pragma mark - --------全场半场 单双 2 FOE_SocOddsId  HOE_SocOddsId
//    NSLog(@"全场半场 单双~~~%@",[dicAllData objectForKey:@"FEven"]);//双
//    NSLog(@"全场半场 单双~~~%@",[dicAllData objectForKey:@"FOdd"]);//单
//    NSLog(@"全场半场 单双~~~%@",[dicAllData objectForKey:@"HEven"]);//双
//    NSLog(@"全场半场 单双~~~%@",[dicAllData objectForKey:@"HOdd"]);//单
        QuanZhuHeKeKey=@[lang.mOdd,lang.mEven];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FOdd"]?[[dicAllData objectForKey:@"FOdd"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FEven"]?[[dicAllData objectForKey:@"FEven"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FOdd"] floatValue]!=0||[[dicAllData objectForKey:@"FEven"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFTOddEven;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        moreBet2ItemsSubview.labUp2.textColor=[UIColor redColor];
        moreBet2ItemsSubview.labUp1.textColor=RGBCOLOR(21, 46, 255);
        if ([[dicAllData objectForKey:@"FOdd"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FEven"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"odd";
        break;
        case 1:
        strType=@"even";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"FOE_SocOddsId"] integerValue]];
        item.mHasPar=sSelf.myItem.mHasPar;
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|5|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|5|%@||",strType,item.mSocOddsId];
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[lang.mOdd,lang.mEven];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HOdd"]?[[dicAllData objectForKey:@"HOdd"] floatValue]/10.0:0],

        冯大川, [20.04.19 14:39]
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HEven"]?[[dicAllData objectForKey:@"HEven"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"HOdd"] floatValue]!=0||[[dicAllData objectForKey:@"HEven"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFHOddEven;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        moreBet2ItemsSubview.labUp2.textColor=[UIColor redColor];
        moreBet2ItemsSubview.labUp1.textColor=RGBCOLOR(21, 46, 255);
        if ([[dicAllData objectForKey:@"HOdd"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"HEven"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"odd";
        break;
        case 1:
        strType=@"even";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HOE_SocOddsId"] integerValue]];
        item.mHasPar=sSelf.myItem.mHasPar;
        item.mHome=sSelf.myItem.mHome;
        item.mAway=sSelf.myItem.mAway;
        NSString * strBETID=[NSString stringWithFormat:@"s|%@|5|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=[NSString stringWithFormat:@"s|%@_par|5|%@||",strType,item.mSocOddsId];
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }

        #pragma mark - --------全场半场 3 HTFT_SocOddsId
        NSDictionary * dicQuanBan=@{
@"AA":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AA"]?[dicAllData objectForKey:@"AA"]:@""],
@"AD":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AD"]?[dicAllData objectForKey:@"AD"]:@""],
@"AH":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AH"]?[dicAllData objectForKey:@"AH"]:@""],
@"DA":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DA"]?[dicAllData objectForKey:@"DA"]:@""],
@"DD":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DD"]?[dicAllData objectForKey:@"DD"]:@""],
@"DH":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DH"]?[dicAllData objectForKey:@"DH"]:@""],
@"HA":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HA"]?[dicAllData objectForKey:@"HA"]:@""],
@"HD":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HD"]?[dicAllData objectForKey:@"HD"]:@""],
@"HH":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HH"]?[dicAllData objectForKey:@"HH"]:@""],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HTFT_SocOddsId"]?[dicAllData objectForKey:@"HTFT_SocOddsId"]:@""],

        冯大川, [20.04.19 14:39]
        };
//    NSLog(@"全场半场~~~%@",dicQuanBan);

        QuanZhuHeKeKey=@[lang.mHH2,lang.mHD2,lang.mHA2];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HH"]?[dicAllData objectForKey:@"HH"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HD"]?[dicAllData objectForKey:@"HD"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HA"]?[dicAllData objectForKey:@"HA"]:@""]];
        if ([[dicAllData objectForKey:@"HH"] floatValue]!=0||[[dicAllData objectForKey:@"HD"] floatValue]!=0||[[dicAllData objectForKey:@"HA"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mHALFTIME_FULLTIME;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"11";
        break;
        case 1:
        strType=@"10";
        break;
        case 2:
        strType=@"12";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HTFT_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|htft|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[lang.mDH2,lang.mDD2,lang.mDA2];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DH"]?[dicAllData objectForKey:@"DH"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DD"]?[dicAllData objectForKey:@"DD"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"DA"]?[dicAllData objectForKey:@"DA"]:@""]];
        if ([[dicAllData objectForKey:@"DH"] floatValue]!=0||[[dicAllData objectForKey:@"DD"] floatValue]!=0||[[dicAllData objectForKey:@"DA"] floatValue]!=0) {

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"1";
        break;
        case 1:
        strType=@"0";
        break;
        case 2:
        strType=@"2";
        break;
default:
        break;
        }

        冯大川, [20.04.19 14:39]
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HTFT_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|htft|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[lang.mAH2,lang.mAD2,lang.mAA2];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AH"]?[dicAllData objectForKey:@"AH"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AD"]?[dicAllData objectForKey:@"AD"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AA"]?[dicAllData objectForKey:@"AA"]:@""]];
        if ([[dicAllData objectForKey:@"AH"] floatValue]!=0||[[dicAllData objectForKey:@"AD"] floatValue]!=0||[[dicAllData objectForKey:@"AA"] floatValue]!=0) {

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"21";
        break;
        case 1:
        strType=@"20";
        break;
        case 2:
        strType=@"22";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HTFT_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|htft|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        #pragma mark - --------最先得分/最后得分 3 FGLG_SocOddsId
        NSDictionary * ZuixianZuihou=@{
@"AF":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AFG"]?[dicAllData objectForKey:@"AFG"]:@""],
@"AL":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"ALG"]?[dicAllData objectForKey:@"ALG"]:@""],
@"HF":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HFG"]?[dicAllData objectForKey:@"HFG"]:@""],
@"HL":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HLG"]?[dicAllData objectForKey:@"HLG"]:@""],
@"NO GOAL":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"NG"]?[dicAllData objectForKey:@"NG"]:@""],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FGLG_SocOddsId"]?[dicAllData objectForKey:@"FGLG_SocOddsId"]:@""],
        };
//    NSLog(@"最先得分/最后得分~~~%@",ZuixianZuihou);

        QuanZhuHeKeKey=@[@"HFG",@"NG",@"AFG"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HLG"]?[dicAllData objectForKey:@"HLG"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"NG"]?[dicAllData objectForKey:@"NG"]:@""],

        冯大川, [20.04.19 14:39]
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"AFG"]?[dicAllData objectForKey:@"AFG"]:@""]];
        if ([[dicAllData objectForKey:@"HLG"] floatValue]!=0||[[dicAllData objectForKey:@"NG"] floatValue]!=0||[[dicAllData objectForKey:@"AFG"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFIRSTGOALLASTGOAL2;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"10";
        break;
        case 1:
        strType=@"0";
        break;
        case 2:
        strType=@"20";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"FGLG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|fglg|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        QuanZhuHeKeKey=@[@"HLG",@"",@"ALG"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HLG"]?[dicAllData objectForKey:@"HLG"]:@""],
        [NSString stringWithFormat:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"ALG"]?[dicAllData objectForKey:@"ALG"]:@""]];
        if ([[dicAllData objectForKey:@"HLG"] floatValue]!=0||[[dicAllData objectForKey:@"ALG"] floatValue]!=0) {

        冯大川, [20.04.19 14:39]
        moreBet3ItemsSubView * moreBet3ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:1];
        [moreBet3ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet3ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet3ItemsSubview];
        WSelf;
        moreBet3ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"1";
        break;
        case 1:
        strType=@"";
        break;
        case 2:
        strType=@"2";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"FGLG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|fglg|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        #pragma mark - --------总进球 2  TG_SocOddsId
        NSDictionary * ZongJinQiu=@{
@"0~1":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal1"]?[dicAllData objectForKey:@"Goal1"]:@""],
@"2~3":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal2"]?[dicAllData objectForKey:@"Goal2"]:@""],
@"4~6":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal3"]?[dicAllData objectForKey:@"Goal3"]:@""],
@"7 & OVER":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal4"]?[dicAllData objectForKey:@"Goal4"]:@""],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"TG_SocOddsId"]?[dicAllData objectForKey:@"TG_SocOddsId"]:@""],
        };
//    NSLog(@"总进球~~~%@",ZongJinQiu);

        QuanZhuHeKeKey=@[@"0~1",@"2~3"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal1"]?[dicAllData objectForKey:@"Goal1"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal2"]?[dicAllData objectForKey:@"Goal2"]:@""]];
        if ([[dicAllData objectForKey:@"Goal1"] floatValue]!=0||[[dicAllData objectForKey:@"Goal2"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mTOTALGOAL2;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        moreBet2ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet2ItemsSubview.labUp2.textColor=[UIColor redColor];
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"1";
        break;
        case 1:
        strType=@"23";

        冯大川, [20.04.19 14:39]
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"TG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|tg|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[@"4~6",@"7 & OVER"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal3"]?[dicAllData objectForKey:@"Goal3"]:@""],
        [NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"Goal4"]?[dicAllData objectForKey:@"Goal4"]:@""]];
        if ([[dicAllData objectForKey:@"Goal3"] floatValue]!=0||[[dicAllData objectForKey:@"Goal4"] floatValue]!=0) {
        //
        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        moreBet2ItemsSubview.labUp1.textColor=[UIColor redColor];
        moreBet2ItemsSubview.labUp2.textColor=[UIColor redColor];
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"46";
        break;
        case 1:
        strType=@"70";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"TG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|tg|1|%@||%@",item.mSocOddsId,strType];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        #pragma mark - --------主队总进球 2  HTTG_SocOddsId HTTG_FHSocOddsId
        NSDictionary * ZhuDuiZongJinQiu=@{
@"FH_O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"HHOOdds"] floatValue]/10],
@"FH_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HTTG_FHOU"]?[dicAllData objectForKey:@"HTTG_FHOU"]:@""],
@"FH_U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"HHUOdds"] floatValue]/10],

@"FT_O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FHOOdds"] floatValue]/10],
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HTTG_OU"]?[dicAllData objectForKey:@"HTTG_OU"]:@""],
@"FT_U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FHUOdds"] floatValue]/10],

@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"HTTG_SocOddsId"]],
@"oid_FH":[NSString stringWithFormat:@"%d",[[dicAllData objectForKey:@"HTTG_SocOddsId"]intValue] +1],
        };
//    NSLog(@"主队总进球~~~%@",ZhuDuiZongJinQiu);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"FT Over",[dicAllData objectForKey:@"HTTG_OU"]],
        [NSString stringWithFormat:@"%@ %@",@"FT Under",[dicAllData objectForKey:@"HTTG_OU"]]];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FHOOdds"]?[[dicAllData objectForKey:@"FHOOdds"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FHUOdds"]?[[dicAllData objectForKey:@"FHUOdds"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FHOOdds"] floatValue]!=0||[[dicAllData objectForKey:@"FHUOdds"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mHOMETEAMTOTALGOALS;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FHOOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FHUOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HTTG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"FH Over",[dicAllData objectForKey:@"HTTG_FHOU"]],
        [NSString stringWithFormat:@"%@ %@",@"FH Uner",[dicAllData objectForKey:@"HTTG_FHOU"]]];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HHOOdds"]?[[dicAllData objectForKey:@"HHOOdds"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HHUOdds"]?[[dicAllData objectForKey:@"HHUOdds"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"HHOOdds"] floatValue]!=0||[[dicAllData objectForKey:@"HHUOdds"] floatValue]!=0) {
        //
        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"HHOOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"HHUOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];

        冯大川, [20.04.19 14:39]
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"HTTG_FHSocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        #pragma mark - --------客队总进球 2  ATTG_SocOddsId ATTG_FHSocOddsId
        NSDictionary * KeduiZongJinQiu=@{
@"FH_O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"HAOOdds"] floatValue]/10],
@"FH_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"ATTG_FHOU"]?[dicAllData objectForKey:@"ATTG_FHOU"]:@""],
@"FH_U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"HAUOdds"] floatValue]/10],
@"FT_O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FAOOdds"] floatValue]/10],
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"ATTG_OU"]?[dicAllData objectForKey:@"ATTG_OU"]:@""],
@"FT_U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FAUOdds"] floatValue]/10],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"ATTG_SocOddsId"]],
@"oid_FH":[NSString stringWithFormat:@"%d",[[dicAllData objectForKey:@"ATTG_SocOddsId"]intValue] +1],
        };
//    NSLog(@"客队总进球~~~%@",KeduiZongJinQiu);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"FT Over",[dicAllData objectForKey:@"ATTG_OU"]],
        [NSString stringWithFormat:@"%@ %@",@"FT Uner",[dicAllData objectForKey:@"ATTG_OU"]]];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FAOOdds"]?[[dicAllData objectForKey:@"FAOOdds"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FAUOdds"]?[[dicAllData objectForKey:@"FAUOdds"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FAOOdds"] floatValue]!=0||[[dicAllData objectForKey:@"FAUOdds"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 200, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mAWAYTEAMTOTALGOALS;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FAOOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FAUOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;

        冯大川, [20.04.19 14:39]
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"ATTG_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"FH Over",[dicAllData objectForKey:@"ATTG_FHOU"]],
        [NSString stringWithFormat:@"%@ %@",@"FH Uner",[dicAllData objectForKey:@"ATTG_FHOU"]]];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HAOOdds"]?[[dicAllData objectForKey:@"HAOOdds"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"HAUOdds"]?[[dicAllData objectForKey:@"HAUOdds"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"HAOOdds"] floatValue]!=0||[[dicAllData objectForKey:@"HAUOdds"] floatValue]!=0) {
        //
        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"HAOOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"HAUOdds"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"ATTG_FHSocOddsId"] integerValue]];
        item.mHasPar=@(0);
            item.mHome=sSelf.myItem.mHome;
                    item.mAway=sSelf.myItem.mAway;
                    NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All+5+rowFor3RowH;
        }

        #pragma mark - --------全场15分钟亚洲盤 & 大/小(00:00-15:00) 2 MG15_SocOddsId
        if ([[dicAllData objectForKey:@"FT15InfoO"] floatValue]!=0&&[[dicAllData objectForKey:@"FT15InfoU"] floatValue]!=0) {
        NSDictionary * FT15MINSHANDICAP_OVER_UNDER_0=@{
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FT15InfoOU"]],
@"O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT15InfoO"] floatValue]/10],

        冯大川, [20.04.19 14:39]
@"U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT15InfoU"] floatValue]/10],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"MG15_SocOddsId"]],
        };
//        NSLog(@"全场15分钟亚洲盤 & 大/小(00:00-15:00)~~~%@",FT15MINSHANDICAP_OVER_UNDER_0);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"大",[dicAllData objectForKey:@"FT15InfoOU"]],
@"小"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT15InfoO"]?[[dicAllData objectForKey:@"FT15InfoO"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT15InfoU"]?[[dicAllData objectForKey:@"FT15InfoU"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FT15InfoO"] floatValue]!=0||[[dicAllData objectForKey:@"FT15InfoU"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 300, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFT15MINSHANDICAP_OVER_UNDER1;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FT15InfoO"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FT15InfoU"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"MG15_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        }

        #pragma mark - --------全场15分钟亚洲盤 & 大/小(15:01-30:00) 2  MG30_SocOddsId
        if ([[dicAllData objectForKey:@"FT30InfoO"] floatValue]!=0&&[[dicAllData objectForKey:@"FT30InfoU"] floatValue]!=0) {
        NSDictionary * FT15MINSHANDICAP_OVER_UNDER_15=@{
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FT30InfoOU"]],
@"O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT30InfoO"] floatValue]/10],
@"U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT30InfoU"] floatValue]/10],

        冯大川, [20.04.19 14:39]
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"MG30_SocOddsId"]],
        };
//        NSLog(@"全场15分钟亚洲盤 & 大/小(15:01-30:00)~~~%@",FT15MINSHANDICAP_OVER_UNDER_15);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"大",[dicAllData objectForKey:@"FT30InfoOU"]],
@"小"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT30InfoO"]?[[dicAllData objectForKey:@"FT30InfoO"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT30InfoU"]?[[dicAllData objectForKey:@"FT30InfoU"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FT30InfoO"] floatValue]!=0||[[dicAllData objectForKey:@"FT30InfoU"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 300, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFT15MINSHANDICAP_OVER_UNDER2;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FT30InfoO"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FT30InfoU"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"MG30_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        }

        #pragma mark - --------全场15分钟亚洲盤 & 大/小(30:01-45:00) 2  MG45_SocOddsId
        if ([[dicAllData objectForKey:@"FT45InfoO"] floatValue]!=0&&[[dicAllData objectForKey:@"FT45InfoU"] floatValue]!=0) {
        NSDictionary * FT15MINSHANDICAP_OVER_UNDER_30=@{
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FT45InfoOU"]],
@"O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT45InfoO"] floatValue]/10],
@"U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT45InfoU"] floatValue]/10],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"MG45_SocOddsId"]],
        };

        冯大川, [20.04.19 14:39]
//        NSLog(@"全场15分钟亚洲盤 & 大/小(30:01-45:00)~~~%@",FT15MINSHANDICAP_OVER_UNDER_30);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"大",[dicAllData objectForKey:@"FT45InfoOU"]],
@"小"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT45InfoO"]?[[dicAllData objectForKey:@"FT45InfoO"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT45InfoU"]?[[dicAllData objectForKey:@"FT45InfoU"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FT45InfoO"] floatValue]!=0||[[dicAllData objectForKey:@"FT45InfoU"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 300, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFT15MINSHANDICAP_OVER_UNDER3;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FT45InfoO"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FT45InfoU"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"MG45_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        }

        冯大川, [20.04.19 14:39]
        #pragma mark - --------全场15分钟亚洲盤 & 大/小(45:01-60:00) 2  MG60_SocOddsId
        if ([[dicAllData objectForKey:@"FT60InfoO"] floatValue]!=0&&[[dicAllData objectForKey:@"FT60InfoU"] floatValue]!=0) {
        NSDictionary * FT15MINSHANDICAP_OVER_UNDER_45=@{
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FT60InfoOU"]],
@"O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT60InfoO"] floatValue]/10],
@"U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT60InfoU"] floatValue]/10],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"MG60_SocOddsId"]],
        };
//        NSLog(@"全场15分钟亚洲盤 & 大/小(45:01-60:00)~~~%@",FT15MINSHANDICAP_OVER_UNDER_45);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"大",[dicAllData objectForKey:@"FT60InfoOU"]],
@"小"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT60InfoO"]?[[dicAllData objectForKey:@"FT60InfoO"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT60InfoU"]?[[dicAllData objectForKey:@"FT60InfoU"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FT60InfoO"] floatValue]!=0||[[dicAllData objectForKey:@"FT60InfoU"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 300, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFT15MINSHANDICAP_OVER_UNDER4;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FT60InfoO"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FT60InfoU"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"MG60_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        }

        #pragma mark - --------全场15分钟亚洲盤 & 大/小(60:01-75:00) 2  MG75_SocOddsId
        if ([[dicAllData objectForKey:@"FT75InfoO"] floatValue]!=0&&[[dicAllData objectForKey:@"FT75InfoU"] floatValue]!=0) {

        冯大川, [20.04.19 14:40]
        你只需关注 我截图的地方

        冯大川, [20.04.19 14:40]
        哪些字段 代表啥

        Tony X, [20.04.19 14:40]
        好的 谢谢  了解了

        冯大川, [20.04.19 14:44]
        NSDictionary * FT15MINSHANDICAP_OVER_UNDER_60=@{
@"FT_OU":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"FT75InfoOU"]],
@"O":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT75InfoO"] floatValue]/10],
@"U":[NSString stringWithFormat:@"%0.2f",[[dicAllData objectForKey:@"FT75InfoU"] floatValue]/10],
@"oid":[NSString stringWithFormat:@"%@",[dicAllData objectForKey:@"MG75_SocOddsId"]],
        };
//        NSLog(@"全场15分钟亚洲盤 & 大/小(60:01-75:00)~~~%@",FT15MINSHANDICAP_OVER_UNDER_60);

        QuanZhuHeKeKey=@[[NSString stringWithFormat:@"%@ %@",@"大",[dicAllData objectForKey:@"FT75InfoOU"]],
@"小"];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT75InfoO"]?[[dicAllData objectForKey:@"FT75InfoO"] floatValue]/10.0:0],
        [NSString stringWithFormat:@"%0.2f",[dicAllData objectForKey:@"FT75InfoU"]?[[dicAllData objectForKey:@"FT75InfoU"] floatValue]/10.0:0]];
        if ([[dicAllData objectForKey:@"FT75InfoO"] floatValue]!=0||[[dicAllData objectForKey:@"FT75InfoU"] floatValue]!=0) {
        //
        UILabel * labName=[[UILabel alloc]initWithFrame:CGRectMake(8, H1All+10, 300, rowFor3RowH)];
        labName.textAlignment=NSTextAlignmentLeft;
        labName.font=[UIFont systemFontOfSize:12.0f];
        labName.text=lang.mFT15MINSHANDICAP_OVER_UNDER5;
        [self.viewForDraw addSubview:labName];

        H1All=H1All+10+rowFor3RowH;

        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        if ([[dicAllData objectForKey:@"FT75InfoO"] floatValue]<0) {
        moreBet2ItemsSubview.labDown1.textColor=[UIColor redColor];
        }
        if ([[dicAllData objectForKey:@"FT75InfoU"] floatValue]<0) {
        moreBet2ItemsSubview.labDown2.textColor=[UIColor redColor];
        }
        [self.viewForDraw addSubview:moreBet2ItemsSubview];
        WSelf;
        moreBet2ItemsSubview.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"over";
        break;
        case 1:
        strType=@"under";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"MG75_SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };
        H1All=H1All-7+rowFor3RowH;
        }
        }

        #pragma mark - --------全场缅甸让球 大小 2
        if ([[Util checkArrBeyondIndex:[dicAllData objectForKey:@"FTMModds"] andIndex:0] isKindOfClass:[NSDictionary class]]) {
        NSDictionary * dicSub=[dicAllData objectForKey:@"FTMModds"][0];
        BOOL hasHDP=NO;

        冯大川, [20.04.19 14:44]
        if ([[dicSub objectForKey:@"HDPH"] length]>0&&[[dicSub objectForKey:@"HomeOdds"] floatValue]!=0&&[[dicSub objectForKey:@"AwayOdds"] floatValue]!=0) {
        hasHDP=YES;
        }
        BOOL hasOU=NO;
        if ([[dicSub objectForKey:@"OU"] length]>0&&[[dicSub objectForKey:@"OverOdds"] floatValue]!=0&&[[dicSub objectForKey:@"UnderOdds"] floatValue]!=0) {
        hasOU=YES;
        }
        if (hasHDP||hasOU) {
        moreBet2ItemsSubView * moreBet2ItemsSubview=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview setFrame:CGRectMake(0, H1All+10, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview titleForItems:@[lang.mFTMyanmarHDP,lang.mFTMyanmarOU]];
        [self.viewForDraw addSubview:moreBet2ItemsSubview];

        H1All=H1All+10+rowFor3RowH;

        QuanZhuHeKeKey=@[hasHDP?[NSString stringWithFormat:@"%@ %@",lang.mHome,[dicSub objectForKey:@"HDPH"]]:lang.mHome,
        hasOU?[NSString stringWithFormat:@"%@ %@",lang.mOver,[dicSub objectForKey:@"OU"]]:lang.mOver];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",hasHDP?[NSString stringWithFormat:@"%0.2f",[[dicSub objectForKey:@"HomeOdds"] floatValue]/10.0]:@""],
        [NSString stringWithFormat:@"%@",hasOU?[NSString stringWithFormat:@"%0.2f",[[dicSub objectForKey:@"OverOdds"] floatValue]/10.0]:@""]];
        moreBet2ItemsSubView * moreBet2ItemsSubview2=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview2 setFrame:CGRectMake(0, H1All-7, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview2 oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet2ItemsSubview2];
        WSelf;
        moreBet2ItemsSubview2.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {
        case 0:
        strType=@"mmhome";
        break;
        case 1:
        strType=@"mmover";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All-7+rowFor3RowH;

        QuanZhuHeKeKey=@[lang.mAway,lang.mUnder];
        QuanZhuHeKeValue=@[[NSString stringWithFormat:@"%@",hasHDP?[NSString stringWithFormat:@"%0.2f",[[dicSub objectForKey:@"AwayOdds"] floatValue]/10.0]:@""],
        [NSString stringWithFormat:@"%@",hasOU?[NSString stringWithFormat:@"%0.2f",[[dicSub objectForKey:@"UnderOdds"] floatValue]/10.0]:@""]];
        moreBet2ItemsSubView * moreBet2ItemsSubview3=[[[NSBundle mainBundle] loadNibNamed:@"dcMorebetView" owner:nil options:nil] objectAtIndex:2];
        [moreBet2ItemsSubview3 setFrame:CGRectMake(0, H1All+5, SCREEN_WITDTH, rowFor3RowH)];
        [moreBet2ItemsSubview3 oddsForArrKeys:QuanZhuHeKeKey andValues:QuanZhuHeKeValue];
        [self.viewForDraw addSubview:moreBet2ItemsSubview3];
        moreBet2ItemsSubview3.btnclick = ^(NSInteger index) {
        SSelf;
        NSString * strType=nil;
        FootBallItem * item=[[FootBallItem alloc]init];
        switch (index) {

        冯大川, [20.04.19 14:44]
        case 0:
        strType=@"mmaway";
        break;
        case 1:
        strType=@"mmunder";
        break;
default:
        break;
        }
        item.mSocOddsId=[NSNumber numberWithInteger:[sSelf.dicForMoreBetInfoo[@"SocOddsId"] integerValue]];
        item.mHasPar=@(0);
                item.mHome=sSelf.myItem.mHome;
                        item.mAway=sSelf.myItem.mAway;
                        NSString * strBETID=[NSString stringWithFormat:@"s|%@|1|%@||",strType,item.mSocOddsId];
        NSString * strBETID_Par=@"";
        if (sSelf.clickBetIndex) {
        //全场...0主场 1大 2客场 3小
        sSelf.clickBetIndex(strBETID,strBETID_Par,item);
        }
        };

        H1All=H1All+5+rowFor3RowH;
        }
        }*/