/*
	Copyright (c) 2004-2009, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dojox.grid._FocusManager"]){
dojo._hasResource["dojox.grid._FocusManager"]=true;
dojo.provide("dojox.grid._FocusManager");
dojo.require("dojox.grid.util");
dojo.declare("dojox.grid._FocusManager",null,{constructor:function(_1){
this.grid=_1;
this.cell=null;
this.rowIndex=-1;
this._connects=[];
this._connects.push(dojo.connect(this.grid.domNode,"onfocus",this,"doFocus"));
this._connects.push(dojo.connect(this.grid.domNode,"onblur",this,"doBlur"));
this._connects.push(dojo.connect(this.grid.lastFocusNode,"onfocus",this,"doLastNodeFocus"));
this._connects.push(dojo.connect(this.grid.lastFocusNode,"onblur",this,"doLastNodeBlur"));
this._connects.push(dojo.connect(this.grid,"_onFetchComplete",this,"_delayedCellFocus"));
this._connects.push(dojo.connect(this.grid,"postrender",this,"_delayedHeaderFocus"));
},destroy:function(){
dojo.forEach(this._connects,dojo.disconnect);
delete this.grid;
delete this.cell;
},_colHeadNode:null,_colHeadFocusIdx:null,tabbingOut:false,focusClass:"dojoxGridCellFocus",focusView:null,initFocusView:function(){
this.focusView=this.grid.views.getFirstScrollingView()||this.focusView;
this._initColumnHeaders();
},isFocusCell:function(_2,_3){
return (this.cell==_2)&&(this.rowIndex==_3);
},isLastFocusCell:function(){
if(this.cell){
return (this.rowIndex==this.grid.rowCount-1)&&(this.cell.index==this.grid.layout.cellCount-1);
}
return false;
},isFirstFocusCell:function(){
if(this.cell){
return (this.rowIndex==0)&&(this.cell.index==0);
}
return false;
},isNoFocusCell:function(){
return (this.rowIndex<0)||!this.cell;
},isNavHeader:function(){
return (!!this._colHeadNode);
},getHeaderIndex:function(){
if(this._colHeadNode){
return dojo.indexOf(this._findHeaderCells(),this._colHeadNode);
}else{
return -1;
}
},_focusifyCellNode:function(_4){
var n=this.cell&&this.cell.getNode(this.rowIndex);
if(n){
dojo.toggleClass(n,this.focusClass,_4);
if(_4){
var sl=this.scrollIntoView();
try{
if(!this.grid.edit.isEditing()){
dojox.grid.util.fire(n,"focus");
if(sl){
this.cell.view.scrollboxNode.scrollLeft=sl;
}
}
}
catch(e){
}
}
}
},_delayedCellFocus:function(){
if(this.isNavHeader()){
return;
}
var n=this.cell&&this.cell.getNode(this.rowIndex);
if(n){
try{
if(!this.grid.edit.isEditing()){
dojo.toggleClass(n,this.focusClass,true);
dojox.grid.util.fire(n,"focus");
}
}
catch(e){
}
}
},_delayedHeaderFocus:function(){
if(this.isNavHeader()){
this.focusHeader();
}
},_initColumnHeaders:function(){
this._connects.push(dojo.connect(this.grid.viewsHeaderNode,"onblur",this,"doBlurHeader"));
var _8=this._findHeaderCells();
for(var i=0;i<_8.length;i++){
this._connects.push(dojo.connect(_8[i],"onfocus",this,"doColHeaderFocus"));
this._connects.push(dojo.connect(_8[i],"onblur",this,"doColHeaderBlur"));
}
},_findHeaderCells:function(){
var _a=dojo.query("th",this.grid.viewsHeaderNode);
var _b=[];
for(var i=0;i<_a.length;i++){
var _d=_a[i];
var _e=dojo.hasAttr(_d,"tabindex");
var _f=dojo.attr(_d,"tabindex");
if(_e&&_f<0){
_b.push(_d);
}
}
return _b;
},scrollIntoView:function(){
var _10=(this.cell?this._scrollInfo(this.cell):null);
if(!_10||!_10.s){
return null;
}
var rt=this.grid.scroller.findScrollTop(this.rowIndex);
if(_10.n&&_10.sr){
if(_10.n.offsetLeft+_10.n.offsetWidth>_10.sr.l+_10.sr.w){
_10.s.scrollLeft=_10.n.offsetLeft+_10.n.offsetWidth-_10.sr.w;
}else{
if(_10.n.offsetLeft<_10.sr.l){
_10.s.scrollLeft=_10.n.offsetLeft;
}
}
}
if(_10.r&&_10.sr){
if(rt+_10.r.offsetHeight>_10.sr.t+_10.sr.h){
this.grid.setScrollTop(rt+_10.r.offsetHeight-_10.sr.h);
}else{
if(rt<_10.sr.t){
this.grid.setScrollTop(rt);
}
}
}
return _10.s.scrollLeft;
},_scrollInfo:function(_12,_13){
if(_12){
var cl=_12,sbn=cl.view.scrollboxNode,_16={w:sbn.clientWidth,l:sbn.scrollLeft,t:sbn.scrollTop,h:sbn.clientHeight},rn=cl.view.getRowNode(this.rowIndex);
return {c:cl,s:sbn,sr:_16,n:(_13?_13:_12.getNode(this.rowIndex)),r:rn};
}
return null;
},_scrollHeader:function(_18){
var _19=null;
if(this._colHeadNode){
var _1a=this.grid.getCell(_18);
_19=this._scrollInfo(_1a,_1a.getNode(0));
}
if(_19&&_19.s&&_19.sr&&_19.n){
var _1b=_19.sr.l+_19.sr.w;
if(_19.n.offsetLeft+_19.n.offsetWidth>_1b){
_19.s.scrollLeft=_19.n.offsetLeft+_19.n.offsetWidth-_19.sr.w;
}else{
if(_19.n.offsetLeft<_19.sr.l){
_19.s.scrollLeft=_19.n.offsetLeft;
}else{
if(dojo.isIE<=7&&_1a&&_1a.view.headerNode){
_1a.view.headerNode.scrollLeft=_19.s.scrollLeft;
}
}
}
}
},styleRow:function(_1c){
return;
},setFocusIndex:function(_1d,_1e){
this.setFocusCell(this.grid.getCell(_1e),_1d);
},setFocusCell:function(_1f,_20){
if(_1f&&!this.isFocusCell(_1f,_20)){
this.tabbingOut=false;
this._colHeadNode=this._colHeadFocusIdx=null;
this.focusGridView();
this._focusifyCellNode(false);
this.cell=_1f;
this.rowIndex=_20;
this._focusifyCellNode(true);
}
if(dojo.isOpera){
setTimeout(dojo.hitch(this.grid,"onCellFocus",this.cell,this.rowIndex),1);
}else{
this.grid.onCellFocus(this.cell,this.rowIndex);
}
},next:function(){
if(this.cell){
var row=this.rowIndex,col=this.cell.index+1,cc=this.grid.layout.cellCount-1,rc=this.grid.rowCount-1;
if(col>cc){
col=0;
row++;
}
if(row>rc){
col=cc;
row=rc;
}
if(this.grid.edit.isEditing()){
var _25=this.grid.getCell(col);
if(!this.isLastFocusCell()&&!_25.editable){
this.cell=_25;
this.rowIndex=row;
this.next();
return;
}
}
this.setFocusIndex(row,col);
}
},previous:function(){
if(this.cell){
var row=(this.rowIndex||0),col=(this.cell.index||0)-1;
if(col<0){
col=this.grid.layout.cellCount-1;
row--;
}
if(row<0){
row=0;
col=0;
}
if(this.grid.edit.isEditing()){
var _28=this.grid.getCell(col);
if(!this.isFirstFocusCell()&&!_28.editable){
this.cell=_28;
this.rowIndex=row;
this.previous();
return;
}
}
this.setFocusIndex(row,col);
}
},move:function(_29,_2a){
if(this.isNavHeader()){
var _2b=this._findHeaderCells();
var _2c=dojo.indexOf(_2b,this._colHeadNode);
_2c+=_2a;
if((_2c>=0)&&(_2c<_2b.length)){
this._colHeadNode=_2b[_2c];
this._colHeadFocusIdx=_2c;
this._scrollHeader(_2c);
this._colHeadNode.focus();
}
}else{
if(this.cell){
var sc=this.grid.scroller,r=this.rowIndex,rc=this.grid.rowCount-1,row=Math.min(rc,Math.max(0,r+_29));
if(_29){
if(_29>0){
if(row>sc.getLastPageRow(sc.page)){
this.grid.setScrollTop(this.grid.scrollTop+sc.findScrollTop(row)-sc.findScrollTop(r));
}
}else{
if(_29<0){
if(row<=sc.getPageRow(sc.page)){
this.grid.setScrollTop(this.grid.scrollTop-sc.findScrollTop(r)-sc.findScrollTop(row));
}
}
}
}
var cc=this.grid.layout.cellCount-1,i=this.cell.index,col=Math.min(cc,Math.max(0,i+_2a));
this.setFocusIndex(row,col);
if(_29){
this.grid.updateRow(r);
}
}
}
},previousKey:function(e){
if(this.grid.edit.isEditing()){
dojo.stopEvent(e);
this.previous();
}else{
if(!this.isNavHeader()){
this.focusHeader();
dojo.stopEvent(e);
}else{
this.tabOut(this.grid.domNode);
}
}
},nextKey:function(e){
var _36=this.grid.rowCount==0;
if(e.target===this.grid.domNode){
this.focusHeader();
dojo.stopEvent(e);
}else{
if(this.isNavHeader()){
this._colHeadNode=this._colHeadFocusIdx=null;
if(this.isNoFocusCell()&&!_36){
this.setFocusIndex(0,0);
}else{
if(this.cell&&!_36){
if(this.focusView&&!this.focusView.rowNodes[this.rowIndex]){
this.grid.scrollToRow(this.rowIndex);
}
this.focusGrid();
}else{
this.tabOut(this.grid.lastFocusNode);
}
}
}else{
if(this.grid.edit.isEditing()){
dojo.stopEvent(e);
this.next();
}else{
this.tabOut(this.grid.lastFocusNode);
}
}
}
},tabOut:function(_37){
this.tabbingOut=true;
_37.focus();
},focusGridView:function(){
dojox.grid.util.fire(this.focusView,"focus");
},focusGrid:function(_38){
this.focusGridView();
this._focusifyCellNode(true);
},focusHeader:function(){
var _39=this._findHeaderCells();
if(!this._colHeadFocusIdx){
if(this.isNoFocusCell()){
this._colHeadFocusIdx=0;
}else{
this._colHeadFocusIdx=this.cell.index;
}
}
this._colHeadNode=_39[this._colHeadFocusIdx];
if(this._colHeadNode){
dojox.grid.util.fire(this._colHeadNode,"focus");
this._focusifyCellNode(false);
}
},doFocus:function(e){
if(e&&e.target!=e.currentTarget){
dojo.stopEvent(e);
return;
}
if(!this.tabbingOut){
this.focusHeader();
}
this.tabbingOut=false;
dojo.stopEvent(e);
},doBlur:function(e){
dojo.stopEvent(e);
},doBlurHeader:function(e){
dojo.stopEvent(e);
},doLastNodeFocus:function(e){
if(this.tabbingOut){
this._focusifyCellNode(false);
}else{
if(this.grid.rowCount>0){
if(this.isNoFocusCell()){
this.setFocusIndex(0,0);
}
this._focusifyCellNode(true);
}else{
this.focusHeader();
}
}
this.tabbingOut=false;
dojo.stopEvent(e);
},doLastNodeBlur:function(e){
dojo.stopEvent(e);
},doColHeaderFocus:function(e){
dojo.toggleClass(e.target,this.focusClass,true);
this._scrollHeader(this.getHeaderIndex());
},doColHeaderBlur:function(e){
dojo.toggleClass(e.target,this.focusClass,false);
}});
}
