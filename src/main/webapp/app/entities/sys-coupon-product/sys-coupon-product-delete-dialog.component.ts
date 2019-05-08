import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';
import { SysCouponProductService } from './sys-coupon-product.service';

@Component({
    selector: 'jhi-sys-coupon-product-delete-dialog',
    templateUrl: './sys-coupon-product-delete-dialog.component.html'
})
export class SysCouponProductDeleteDialogComponent {
    sysCouponProduct: ISysCouponProduct;

    constructor(
        protected sysCouponProductService: SysCouponProductService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCouponProductService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCouponProductListModification',
                content: 'Deleted an sysCouponProduct'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-coupon-product-delete-popup',
    template: ''
})
export class SysCouponProductDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponProduct }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCouponProductDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysCouponProduct = sysCouponProduct;
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
