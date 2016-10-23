/**
 * apk 下载
 */
private void startDownloadApk() {
        String url = HttpUrlConst.COMMON_GETAPK;
        String tempUrl = versionJson.lastAppURL;
        if(!TextUtils.isEmpty(tempUrl)){
            url = tempUrl;
        }
        LogUtil.e("downurl="+url);

        if (pd == null) {
            pd = new ProgressDialog(BaseAppUpdateActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("正在更新");
            pd.setMessage("正在更新应用程序");
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            pd.setMax(100);
        }

        FileCallBack<ResponseBody> fileCallBack = new FileCallBack<ResponseBody>() {
            @Override
            public void onSuccess(File apkFile) {
                if (pd != null) {
                    pd.dismiss();
                }
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
                BaseAppUpdateActivity.this.startActivity(installIntent);
                //退出整个app
                finishAllActivity();
            }

            @Override
            public void onProgress(long progress, long total) {
                long cent = progress * 100 / total;
                pd.setProgress((int) cent);
            }

            @Override
            public void onFail(Call<ResponseBody> call, Throwable t) {
                if (pd != null) {
                    pd.dismiss();
                }
                ToastUtil.showShortToast("网络连接失败");
            }
        };

        ApiUtils.getDownloadApkService(fileCallBack)
                .downloadApk(url)
                .enqueue(fileCallBack);
}






    /**
     * 图片上传
     *
     * @param path path
     * @param cvId cvId
     */
    public void uploadCImagePresenter(String path, String cvId) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (null != mWeakReferenceClipImageActivity) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("flieName", file.getName(), requestFile);
                RequestBody cvid = RequestBody.create(MediaType.parse("multipart/form-data"), cvId);

                ApiUtils.getMyApiService()
                        .uploadCImage(cvid, body)
                        .enqueue(new ChinaHrCallBack<PhotoBean>() {
                            @Override
                            public void onSuccess(Call<PhotoBean> call, Response<PhotoBean> response) {
                                DialogUtil.closeProgress();
                                PhotoBean photoBean = response.body();
                                CropImageActivity activity = mWeakReferenceClipImageActivity.get();
                                if (photoBean.code == 0 && photoBean.data != null && !TextUtils.isEmpty(photoBean.data.photo)) {
                                    if (activity != null) {
                                        activity.upLoadSucess(photoBean.data.photo);
                                    }
                                } else {
                                    if (activity != null) {
                                        activity.upLoadFail();
                                    }
                                }
                            }

                            @Override
                            public void onFail(Call<PhotoBean> call, Throwable t) {
                                DialogUtil.closeProgress();
                                if (null != mWeakReferenceClipImageActivity) {
                                    CropImageActivity activity = mWeakReferenceClipImageActivity.get();
                                    if (activity != null) {
                                        activity.upLoadFail();
                                    }
                                }
                            }
                        });
            }
        }
    }

    /**
     * 图片上传
     *
     * @param path 图片路径
     */
    public void uploadBImagePresenter(String path) {
        if (!TextUtils.isEmpty(path)) {
            final File file = new File(path);
            if (null != mWeakReferenceClipImageActivity) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                
                ApiUtils.getQyDomainService()
                        .uploadBImage(body)
                        .enqueue(new ChinaHrCallBack<PhotoBean>() {
                            @Override
                            public void onSuccess(Call<PhotoBean> call, Response<PhotoBean> response) {
                                DialogUtil.closeProgress();
                                PhotoBean photoBean = response.body();
                                if (null != mWeakReferenceClipImageActivity) {
                                    CropImageActivity activity = mWeakReferenceClipImageActivity.get();
                                    if (photoBean.code == 0 && photoBean.data != null && !TextUtils.isEmpty(photoBean.data.photoPath)) {
                                        if (activity != null) {
                                            activity.upLoadSucess(photoBean.data.photoPath);
                                        }
                                    } else {
                                        if (activity != null) {
                                            activity.upLoadFail();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFail(Call<PhotoBean> call, Throwable t) {
                                DialogUtil.closeProgress();
                                if (null != mWeakReferenceClipImageActivity) {
                                    CropImageActivity activity = mWeakReferenceClipImageActivity.get();
                                    if (activity != null) {
                                        activity.upLoadFail();
                                    }
                                }
                            }
                        });
            }
        }
    }