# MultiItemViewPager
MultiItemViewPager，显示多个item的ViewPager例子


第一种：
继承了ViewPager进行拓展，主要是设置了setClipToPadding(false)，setPadding(paddind, 0, paddind, 0)，setOffscreenPageLimit()
使ViewPager绘制区域居中，并且item会绘制到paddind区域

第二种：
主要是用PagerAdapter中的public float getPageWidth(int position)，PageWidth按相对屏幕的比例返回(0,1)，
如果要是第一个item居中还有设置setClipToPadding(false)和setPadding(paddind, 0, 0, 0)
