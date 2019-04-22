/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductImageDeleteDialogComponent } from 'app/entities/sys-product-image/sys-product-image-delete-dialog.component';
import { SysProductImageService } from 'app/entities/sys-product-image/sys-product-image.service';

describe('Component Tests', () => {
    describe('SysProductImage Management Delete Component', () => {
        let comp: SysProductImageDeleteDialogComponent;
        let fixture: ComponentFixture<SysProductImageDeleteDialogComponent>;
        let service: SysProductImageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductImageDeleteDialogComponent]
            })
                .overrideTemplate(SysProductImageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysProductImageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysProductImageService);
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
