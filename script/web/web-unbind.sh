#!/bin/bash
set -e
DEPLOY_USER=ubuntu
echo "DEPLOY USER = $DEPLOY_USER"

ZIP_DIR='/home/'$DEPLOY_USER'/app/zip'
sudo mkdir -p $ZIP_DIR
echo "ZIP_DIR = $ZIP_DIR"

sudo mv '/home/'$DEPLOY_USER'/web.tar' $ZIP_DIR/
echo "tar moved to $ZIP_DIR"
sudo tar -xvf $ZIP_DIR/web.tar -C $ZIP_DIR/
echo "> done untar"

echo "> run deploy.sh"
sudo sh $ZIP_DIR/deploy.sh