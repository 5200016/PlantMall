import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { SysReceiverAddressService } from './sys-receiver-address.service';

@Component({
    selector: 'jhi-sys-receiver-address-delete-dialog',
    templateUrl: './sys-receiver-address-delete-dialog.component.html'
})
export class SysReceiverAddressDeleteDialogComponent {
    sysReceiverAddress: ISysReceiverAddress;

    constructor(
        protected sysReceiverAddressService: SysReceiverAddressService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysReceiverAddressService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysReceiverAddressListModification',
                content: 'Deleted an sysReceiverAddress'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-receiver-address-delete-popup',
    template: ''
})
export class SysReceiverAddressDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysReceiverAddress }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysReceiverAddressDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysReceiverAddress = sysReceiverAddress;
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
