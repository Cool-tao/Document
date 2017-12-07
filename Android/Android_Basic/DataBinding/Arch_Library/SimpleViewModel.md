> SimpleViewModel
```
abstract class SimpleViewModel<V : AbsContract.View, P : AbsContract.Presenter>(view: V) :
        AbsViewModel<V, P>(view) {

    abstract override fun bindView(activity: FragmentActivity, resourceProvider: AbsContract.ResourceProvider)

    abstract override fun onClickEvent(v: View)

    abstract override fun onCreateData(savedInstanceState: Bundle)

    override fun onDestroy() {

    }
}
```

> SimpleActivity
```

abstract class SimpleActivity<VM : AbsContract.ViewModel> :
        AbsActivity<VM>() {
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        loadingDialog = LoadingDialog(this)
        super.onCreate(savedInstanceState)
    }

    abstract override fun createViewModel(): VM

    abstract override fun onCreateData(savedInstanceState: Bundle);

    override fun showLoadingView() {
        loadingDialog.show()
    }

    override fun dismissLoadingView() {
        loadingDialog.dismiss()
    }

}
```