import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';
import { SysOrderProductService } from './sys-order-product.service';

@Component({
    selector: 'jhi-sys-order-product-delete-dialog',
    templateUrl: './sys-order-product-delete-dialog.component.html'
})
export class SysOrderProductDeleteDialogComponent {
    sysOrderProduct: ISysOrderProduct;

    constructor(
        protected sysOrderProductService: SysOrderProductService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysOrderProductService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysOrderProductListModification',
                content: 'Deleted an sysOrderProduct'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-order-product-delete-popup',
    template: ''
})
export class SysOrderProductDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysOrderProduct }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysOrderProductDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysOrderProduct = sysOrderProduct;
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
