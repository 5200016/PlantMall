/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysReceiverAddressDeleteDialogComponent } from 'app/entities/sys-receiver-address/sys-receiver-address-delete-dialog.component';
import { SysReceiverAddressService } from 'app/entities/sys-receiver-address/sys-receiver-address.service';

describe('Component Tests', () => {
    describe('SysReceiverAddress Management Delete Component', () => {
        let comp: SysReceiverAddressDeleteDialogComponent;
        let fixture: ComponentFixture<SysReceiverAddressDeleteDialogComponent>;
        let service: SysReceiverAddressService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReceiverAddressDeleteDialogComponent]
            })
                .overrideTemplate(SysReceiverAddressDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysReceiverAddressDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysReceiverAddressService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
