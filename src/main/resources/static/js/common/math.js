// 封装常用数学处理方法
// 如：(众数)Math.mode(nums);
var MathUtil = function(){
	
	return {
		test: function(){
//			console.group("众数测试");
//				var nums = [1,5,3,3,4,3,5,1,2];
//				console.log(MathUtil.mode(nums));
//			console.groupEnd(); 
			
//			console.group("中位数测试");
//				var nums = [1,5,3,3,4,3,5,1];
//				console.log(MathUtil.median(nums));
//			console.groupEnd();
			
//			console.group("均值测试");
//				var nums = [1,5,3,3,4,3,5,1];
//				console.log(MathUtil.avg(nums));
//			console.groupEnd();
			
//			console.group("缺失值填充测试");
//				var nums = [1,2,4,5,,2,,1,3];
//				console.log(MathUtil.fixArray(nums));
//			console.groupEnd();
			
			console.group("排序测试");
				var nums = [1,5,3,3,4,3,5,1];
				console.log(MathUtil.sortNumArray(nums, 'desc'));
			console.groupEnd();
			
		},
		// 众数
		mode: function(nums){ // 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数最多的元素
			// 1、双重循环遍历数组的值，并依次记录每个数在数组中总共出现的次数，并将总次数按顺序插入至新的数组中。
			// 2、通过找出数组中的最大值，即可获得最大值的索引值
			// 3、最终通过索引取得数组中的众数。
			
			if(!nums || nums.length < 1) return -1;
			
			var resulteArr = [];
			nums.forEach(item => {
				var count = 0;
				nums.forEach(hash => {
					if (item === hash) {
						count++;
					}
				});
				resulteArr.push(count);  
			});
			var max = Math.max.apply(null, resulteArr);  
			var index = resulteArr.findIndex(item => item === max);  // 取最靠前的一位作为众数
			return nums[index];
		},
		// 数组排序
		sortNumArray: function(nums, sort){ // 默认从小到大
			if(!nums || nums.length < 1)return -1;
			
			return nums.sort(function(a, b){
				if(!sort || sort=='asc' || sort <1){
					return a-b;
				}else{
					return b-a;
				}
			});
		},
		// 中位数
		median: function(nums){ // 排序后中间的数
			if(!nums || nums.length < 1)return -1;
			
			nums = MathUtil.sortNumArray(nums); 
			if(nums.length %2 == 1){
				return nums[(nums.length-1)/2];
			}else {
				return (nums[nums.length/2 -1] + nums[nums.length/2]).toFixed(4);
			}
		},
		// 总值
		sum: function(nums){
			if(!nums || nums.length < 1)return -1;
			
			var sum = 0;
			nums.forEach(item => {
				sum += item;
			});
			return sum;
		},
		// 均值
		avg: function(nums){
			if(!nums || nums.length < 1)return -1;
			
			return (MathUtil.sum(nums)/nums.length).toFixed(4);
		},
		findExistIndex: function(nums, beginIndex){
			for(let j=beginIndex;j<nums.length;j++){
				if(nums[j]){
					return j;
				}
			}
			return -1;
		},
		// 最近可获得值(缺失值填充) 
		fixArray: function(nums){  // [1,2,4,5,,2,,1,3] --> [1, 2, 4, 5, "3.5000", 2, "1.5000", 1, 3]
			if(!nums || nums.length < 1)return -1;
			
			var i=0;
			for(;i<nums.length;i++){
				if(i==0 && !nums[0]){// 第一值为缺失值
					if(nums[i+1]){// 下一个不是缺失值
						nums[0] = nums[i+1];
						i++;
					}else{
						var existIndex = MathUtil.findExistIndex(nums, i+2);
						if(existIndex > -1){
							var t=i;
							for(;t<existIndex;t++){
								nums[t] = nums[existIndex];
							}
							if(t==existIndex-1)i = existIndex+1;
						}else{
							return -1;
						}
					}
				}else if(!nums[i]){// 找到了缺失值
					if(i==nums.length-1){// 最后的值为缺失值
						nums[i] = nums[i-1];
					}else {// 中间值为缺失值
						if(nums[i+1]){// 后一个不是缺失值
							nums[i] = ((nums[i-1] + nums[i+1])/2).toFixed(4);
							i++;
						}else{
							var existIndex = MathUtil.findExistIndex(nums, i+2);
							var t=i;
							if(existIndex > -1){
								var tvalue = ((nums[i-1] + nums[existIndex])/2).toFixed(4);
								for(;t<existIndex;t++){
									nums[t] = tvalue;
								}
							}else{
								for(;t<nums.length;t++){
									nums[t] = nums[i-1];
								}
							}
							if(t==existIndex-1)i = existIndex+1;
						}
					}
				}
			}
			
			if(i >= nums.length) return nums;
		}
	}
}();