# normalization-vs-standardization


## Good video
- https://www.youtube.com/watch?v=bqhQ2LWBheQ
- https://www.youtube.com/watch?v=wFuBUbfixzU

Are below points true?

- scaling (standardization or normalization) is required when our algorithms use gradient calculation like neural networks and regression???

- scaling is not required for distance based and tree based algorithms??
	- k-means clustering
	- support vector machines
	- decision trees

This one does not seem very true

when to choose?
for neural networks standardization since NN does not assume data distribution???

when outliers standardization is preferred 

Note again on scaling from following video: 
https://www.youtube.com/watch?v=wFuBUbfixzU


- The coefficients of linear models are influenced by the scale of the variable.
- Variables with bigger magnitude dominate over those with smaller magnitude
- Gradient descent converges much faster on scaled data
- Feature scaling decrease the time to find support vectors for SVMs
- Euclidean distances are sensitive to feature magnitude.
- PCA require the features to be centered at 0.
- compute data