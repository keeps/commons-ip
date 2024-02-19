# DEV Notes

## How to release a new version

```bash
git tag 1.0.0 # Replace with the newer version

git push origin 1.0.0
```

This will trigger a CI/CD pipeline where it will:

* Build & Packaging the JDK library and CLI
* Create a draft GitHub release

After the pipeline ends please do the following step:

* Update the release
* Update the CHANGELOG.md
* Run the prepare next version script
