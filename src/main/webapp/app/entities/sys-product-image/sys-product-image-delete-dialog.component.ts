import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysProductImage } from 'app/shared/model/sys-product-image.model';
import { SysProductImageService } from './sys-product-image.service';

@Component({
    selector: 'jhi-sys-product-image-delete-dialog',
    templateUrl: './sys-product-image-delete-dialog.component.html'
})
export class SysProductImageDeleteDialogComponent {
    sysProductImage: ISysProductImage;

    constructor(
        protected sysProductImageService: SysProductImageService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysProductImageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysProductImageListModification',
                content: 'Deleted an sysProductImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-product-image-delete-popup',
    template: ''
})
export class SysProductImageDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysProductImage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysProductImageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysProductImage = sysProductImage;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
